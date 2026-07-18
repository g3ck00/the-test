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
        <Modal
            isOpen={isOpen}
            title="Crear nueva póliza"
            onClose={handleClose}
        >
            <form
                onSubmit={handleSubmit(onSubmit)}
                style={{
                    display: "flex",
                    flexDirection: "column",
                    gap: "20px",
                    minWidth: "520px",
                }}
            >
                <div
                    style={{
                        background: "#f8fafc",
                        border: "1px solid #e2e8f0",
                        borderRadius: "14px",
                        padding: "16px",
                    }}
                >
                    <h3
                        style={{
                            margin: 0,
                            color: "#1e293b",
                            fontSize: "1rem",
                        }}
                    >
                        Información de la póliza
                    </h3>

                    <p
                        style={{
                            margin: "6px 0 0",
                            color: "#64748b",
                            fontSize: "0.9rem",
                        }}
                    >
                        Registra los datos principales del nuevo seguro.
                    </p>
                </div>


                <div
                    style={{
                        background: "#ffffff",
                        border: "1px solid #e2e8f0",
                        borderRadius: "14px",
                        padding: "18px",
                        display: "flex",
                        flexDirection: "column",
                        gap: "14px",
                    }}
                >
                    <Campo
                        label="Código de póliza"
                        error={errors.codigoPoliza?.message}
                    >
                        <input
                            {...register("codigoPoliza")}
                            placeholder="POL-0001"
                        />
                    </Campo>

                    <div
                        style={{
                            display: "grid",
                            gridTemplateColumns: "1fr 1fr",
                            gap: "14px",
                        }}
                    >
                        <Campo
                            label="Asegurado"
                            error={errors.nombreAsegurado?.message}
                        >
                            <input {...register("nombreAsegurado")} />
                        </Campo>

                        <Campo
                            label="Beneficiario"
                            error={errors.nombreBeneficiario?.message}
                        >
                            <input {...register("nombreBeneficiario")} />
                        </Campo>
                    </div>

                    <Campo
                        label="Tipo de seguro"
                        error={errors.tipoSeguro?.message}
                    >
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
                    </Campo>
                </div>


                <div
                    style={{
                        background: "#ffffff",
                        border: "1px solid #e2e8f0",
                        borderRadius: "14px",
                        padding: "18px",
                    }}
                >
                    <h3
                        style={{
                            margin: "0 0 14px",
                            fontSize: "1rem",
                            color: "#1e293b",
                        }}
                    >
                        Valores del seguro
                    </h3>

                    <div
                        style={{
                            display: "grid",
                            gridTemplateColumns: "1fr 1fr",
                            gap: "14px",
                        }}
                    >
                        <Campo
                            label="Prima mensual (USD)"
                            error={errors.primaMensual?.message}
                        >
                            <input
                                type="number"
                                step="0.01"
                                min="0"
                                {...register("primaMensual", {
                                    valueAsNumber: true,
                                })}
                            />
                        </Campo>

                        <Campo
                            label="Cobertura (USD)"
                            error={errors.cobertura?.message}
                        >
                            <input
                                type="number"
                                step="0.01"
                                min="0"
                                {...register("cobertura", {
                                    valueAsNumber: true,
                                })}
                            />
                        </Campo>
                    </div>
                </div>


                <div
                    style={{
                        background: "#ffffff",
                        border: "1px solid #e2e8f0",
                        borderRadius: "14px",
                        padding: "18px",
                    }}
                >
                    <h3
                        style={{
                            margin: "0 0 14px",
                            fontSize: "1rem",
                            color: "#1e293b",
                        }}
                    >
                        Vigencia
                    </h3>

                    <div
                        style={{
                            display: "grid",
                            gridTemplateColumns: "1fr 1fr",
                            gap: "14px",
                        }}
                    >
                        <Campo
                            label="Fecha de inicio"
                            error={errors.fechaInicio?.message}
                        >
                            <input type="date" {...register("fechaInicio")} />
                        </Campo>

                        <Campo
                            label="Fecha de vencimiento"
                            error={errors.fechaVencimiento?.message}
                        >
                            <input
                                type="date"
                                min={hoy}
                                {...register("fechaVencimiento")}
                            />
                        </Campo>
                    </div>
                </div>


                <Campo
                    label="Descripción"
                    error={errors.descripcionPoliza?.message}
                >
            <textarea
                rows={4}
                {...register("descripcionPoliza")}
                placeholder="Detalles adicionales..."
            />
                </Campo>


                {errorGeneral && (
                    <div
                        style={{
                            background: "#fee2e2",
                            color: "#991b1b",
                            padding: "12px",
                            borderRadius: "10px",
                            border: "1px solid #fecaca",
                        }}
                    >
                        {errorGeneral}
                    </div>
                )}


                <div
                    style={{
                        display: "flex",
                        justifyContent: "flex-end",
                        gap: "12px",
                        paddingTop: "8px",
                        borderTop: "1px solid #e2e8f0",
                    }}
                >
                    <button
                        type="button"
                        onClick={handleClose}
                        style={{
                            padding: "11px 18px",
                            borderRadius: "10px",
                            border: "1px solid #cbd5e1",
                            background: "#fff",
                            color: "#475569",
                            cursor: "pointer",
                            fontWeight: 600,
                        }}
                    >
                        Cancelar
                    </button>

                    <button
                        type="submit"
                        disabled={isSubmitting}
                        style={{
                            padding: "11px 22px",
                            borderRadius: "10px",
                            border: "none",
                            background: isSubmitting
                                ? "#94a3b8"
                                : "#2563eb",
                            color: "white",
                            cursor: isSubmitting
                                ? "not-allowed"
                                : "pointer",
                            fontWeight: 700,
                        }}
                    >
                        {isSubmitting ? "Guardando..." : "Crear póliza"}
                    </button>
                </div>
            </form>
        </Modal>
    );
}

function Campo({
                   label,
                   error,
                   children,
               }: {
    label: string;
    error?: string;
    children: React.ReactNode;
}) {
    return (
        <label
            style={{
                display: "flex",
                flexDirection: "column",
                gap: "6px",
                color: "#334155",
                fontSize: "0.9rem",
                fontWeight: 600,
            }}
        >
            {label}

            <div
                style={{
                    fontWeight: 400,
                }}
            >
                {children}
            </div>

            {error && (
                <span
                    style={{
                        color: "#dc2626",
                        fontSize: "0.8rem",
                        fontWeight: 500,
                    }}
                >
                    {error}
                </span>
            )}
        </label>
    );
}