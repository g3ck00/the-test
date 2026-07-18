import { useState } from "react";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import Modal from "./Modal.js";
import { polizaSchema, type PolizaFormData } from "../utils/validations.js";
import { TIPOS_SEGURO } from "../types/poliza.js";

interface PolizaFormModalProps {
    isOpen: boolean;
    onClose: () => void;
    onCrear: (data: PolizaFormData) => Promise<void>;
}

const hoy = new Date().toISOString().slice(0, 10);

export default function PolizaFormModal({
    isOpen,
    onClose,
    onCrear,
}: PolizaFormModalProps) {
    const [errorGeneral, setErrorGeneral] = useState<string | null>(null);

    const {
        register,
        handleSubmit,
        reset,
        formState: { errors, isSubmitting },
    } = useForm<PolizaFormData>({
        resolver: zodResolver(polizaSchema),
        defaultValues: {
            fechaInicio: hoy,
        },
    });

    async function onSubmit(data: PolizaFormData) {
        try {
            setErrorGeneral(null);
            await onCrear(data);
            reset({ fechaInicio: hoy });
            onClose();
        } catch {
            setErrorGeneral("No se pudo crear la póliza. Verifica los datos.");
        }
    }

    function handleClose() {
        reset({ fechaInicio: hoy });
        setErrorGeneral(null);
        onClose();
    }

    return (
        <Modal isOpen={isOpen} title="Crear nueva póliza" onClose={handleClose}>
            <form className="form" onSubmit={handleSubmit(onSubmit)}>
                <label>
                    Código de póliza
                    <input {...register("codigoPoliza")} placeholder="POL-0001" />
                    {errors.codigoPoliza && (
                        <span className="form-error">{errors.codigoPoliza.message}</span>
                    )}
                </label>

                <label>
                    Nombre del asegurado
                    <input {...register("nombreAsegurado")} />
                    {errors.nombreAsegurado && (
                        <span className="form-error">
                            {errors.nombreAsegurado.message}
                        </span>
                    )}
                </label>

                <label>
                    Nombre del beneficiario
                    <input {...register("nombreBeneficiario")} />
                    {errors.nombreBeneficiario && (
                        <span className="form-error">
                            {errors.nombreBeneficiario.message}
                        </span>
                    )}
                </label>

                <label>
                    Tipo de seguro
                    <select {...register("tipoSeguro")} defaultValue="">
                        <option value="" disabled>
                            Selecciona un tipo
                        </option>
                        {TIPOS_SEGURO.map((tipo) => (
                            <option key={tipo} value={tipo}>
                                {tipo}
                            </option>
                        ))}
                    </select>
                    {errors.tipoSeguro && (
                        <span className="form-error">{errors.tipoSeguro.message}</span>
                    )}
                </label>

                <div className="form-row">
                    <label>
                        Prima mensual (USD)
                        <input
                            type="number"
                            step="0.01"
                            min="0"
                            {...register("primaMensual", { valueAsNumber: true })}
                        />
                        {errors.primaMensual && (
                            <span className="form-error">
                                {errors.primaMensual.message}
                            </span>
                        )}
                    </label>

                    <label>
                        Cobertura (USD)
                        <input
                            type="number"
                            step="0.01"
                            min="0"
                            {...register("cobertura", { valueAsNumber: true })}
                        />
                        {errors.cobertura && (
                            <span className="form-error">{errors.cobertura.message}</span>
                        )}
                    </label>
                </div>

                <div className="form-row">
                    <label>
                        Fecha de inicio
                        <input type="date" {...register("fechaInicio")} />
                        {errors.fechaInicio && (
                            <span className="form-error">
                                {errors.fechaInicio.message}
                            </span>
                        )}
                    </label>

                    <label>
                        Fecha de vencimiento
                        <input type="date" min={hoy} {...register("fechaVencimiento")} />
                        {errors.fechaVencimiento && (
                            <span className="form-error">
                                {errors.fechaVencimiento.message}
                            </span>
                        )}
                    </label>
                </div>

                <label>
                    Descripción
                    <textarea rows={3} {...register("descripcionPoliza")} />
                    {errors.descripcionPoliza && (
                        <span className="form-error">
                            {errors.descripcionPoliza.message}
                        </span>
                    )}
                </label>

                {errorGeneral && <p className="form-error">{errorGeneral}</p>}

                <div className="form-actions">
                    <button
                        type="button"
                        className="btn btn-secondary"
                        onClick={handleClose}
                    >
                        Cancelar
                    </button>
                    <button type="submit" className="btn btn-primary" disabled={isSubmitting}>
                        {isSubmitting ? "Guardando..." : "Crear póliza"}
                    </button>
                </div>
            </form>
        </Modal>
    );
}
