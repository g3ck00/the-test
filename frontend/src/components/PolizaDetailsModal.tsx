import { useState } from "react";
import Modal from "./Modal.js";
import type { Poliza } from "../types/poliza.js";
import { formatDate, formatMoney } from "../utils/format.js";

interface PolizaDetailsModalProps {
    poliza: Poliza | null;
    onClose: () => void;
    onEliminar: (id: number) => Promise<void>;
}

export default function PolizaDetailsModal({
    poliza,
    onClose,
    onEliminar,
}: PolizaDetailsModalProps) {
    const [eliminando, setEliminando] = useState(false);

    if (!poliza) return null;

    async function handleEliminar() {
        if (!poliza) return;

        const confirmar = window.confirm(
            `¿Eliminar la póliza ${poliza.codigoPoliza}? Esta acción no se puede deshacer.`
        );

        if (!confirmar) return;

        try {
            setEliminando(true);
            await onEliminar(poliza.idPoliza);
            onClose();
        } finally {
            setEliminando(false);
        }
    }

    return (
        <Modal isOpen={!!poliza} title={`Póliza ${poliza.codigoPoliza}`} onClose={onClose}>
            <div className="detalle-grid">
                <p><strong>Asegurado:</strong> {poliza.nombreAsegurado}</p>
                <p><strong>Beneficiario:</strong> {poliza.nombreBeneficiario}</p>
                <p><strong>Tipo de seguro:</strong> {poliza.tipoSeguro}</p>
                <p><strong>Estado:</strong> {poliza.estadoSeguro}</p>
                <p><strong>Prima mensual:</strong> {formatMoney(poliza.primaMensual)}</p>
                <p><strong>Cobertura:</strong> {formatMoney(poliza.cobertura)}</p>
                <p><strong>Fecha de inicio:</strong> {formatDate(poliza.fechaInicio)}</p>
                <p><strong>Fecha de vencimiento:</strong> {formatDate(poliza.fechaVencimiento)}</p>
            </div>

            <p className="detalle-descripcion">
                <strong>Descripción:</strong> {poliza.descripcionPoliza}
            </p>

            <hr className="detalle-separador" />

            <div className="detalle-auditoria">
                <p>Creada por {poliza.creadorPoliza ?? "-"} el {formatDate(poliza.fechaCreacionPoliza)}</p>
                {poliza.modificadorRegistroPoliza && (
                    <p>
                        Modificada por {poliza.modificadorRegistroPoliza} el{" "}
                        {formatDate(poliza.fechaModificacionPoliza)}
                    </p>
                )}
            </div>

            <button
                type="button"
                className="btn btn-danger"
                onClick={handleEliminar}
                disabled={eliminando}
            >
                {eliminando ? "Eliminando..." : "Eliminar póliza"}
            </button>
        </Modal>
    );
}
