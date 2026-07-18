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
        <Modal
            isOpen={!!poliza}
            title={`Póliza ${poliza.codigoPoliza}`}
            onClose={onClose}
        >
            <div
                style={{
                    display: "flex",
                    flexDirection: "column",
                    gap: "20px",
                    minWidth: "420px",
                }}
            >
                <div
                    style={{
                        background: "#f8fafc",
                        borderRadius: "14px",
                        padding: "18px",
                        display: "grid",
                        gridTemplateColumns: "1fr 1fr",
                        gap: "14px",
                        border: "1px solid #e2e8f0",
                    }}
                >
                    <Detalle
                        titulo="Asegurado"
                        valor={poliza.nombreAsegurado}
                    />

                    <Detalle
                        titulo="Beneficiario"
                        valor={poliza.nombreBeneficiario}
                    />

                    <Detalle
                        titulo="Tipo de seguro"
                        valor={poliza.tipoSeguro}
                    />

                    <Detalle
                        titulo="Estado"
                        valor={poliza.estadoSeguro}
                        destacado
                    />

                    <Detalle
                        titulo="Prima mensual"
                        valor={formatMoney(poliza.primaMensual)}
                    />

                    <Detalle
                        titulo="Cobertura"
                        valor={formatMoney(poliza.cobertura)}
                    />

                    <Detalle
                        titulo="Inicio"
                        valor={formatDate(poliza.fechaInicio)}
                    />

                    <Detalle
                        titulo="Vencimiento"
                        valor={formatDate(poliza.fechaVencimiento)}
                    />
                </div>

                <div
                    style={{
                        background: "#ffffff",
                        borderRadius: "12px",
                        padding: "16px",
                        border: "1px solid #e2e8f0",
                    }}
                >
                    <h4
                        style={{
                            margin: "0 0 8px",
                            color: "#334155",
                            fontSize: "0.95rem",
                        }}
                    >
                        Descripción
                    </h4>

                    <p
                        style={{
                            margin: 0,
                            color: "#64748b",
                            lineHeight: 1.5,
                        }}
                    >
                        {poliza.descripcionPoliza || "Sin descripción"}
                    </p>
                </div>

                <div
                    style={{
                        paddingTop: "12px",
                        borderTop: "1px solid #e2e8f0",
                        color: "#64748b",
                        fontSize: "0.85rem",
                    }}
                >
                    <p style={{ margin: "4px 0" }}>
                        Creada por{" "}
                        <strong>{poliza.creadorPoliza ?? "-"}</strong>{" "}
                        el {formatDate(poliza.fechaCreacionPoliza)}
                    </p>

                    {poliza.modificadorRegistroPoliza && (
                        <p style={{ margin: "4px 0" }}>
                            Modificada por{" "}
                            <strong>{poliza.modificadorRegistroPoliza}</strong>{" "}
                            el {formatDate(poliza.fechaModificacionPoliza)}
                        </p>
                    )}
                </div>

                <button
                    type="button"
                    onClick={handleEliminar}
                    disabled={eliminando}
                    style={{
                        width: "100%",
                        marginTop: "8px",
                        padding: "12px",
                        borderRadius: "12px",
                        border: "none",
                        background: eliminando ? "#94a3b8" : "#dc2626",
                        color: "white",
                        fontWeight: 700,
                        cursor: eliminando ? "not-allowed" : "pointer",
                        fontSize: "0.95rem",
                        transition: "all .2s ease",
                    }}
                >
                    {eliminando ? "Eliminando..." : "Eliminar póliza"}
                </button>
            </div>
        </Modal>
    );
}

function Detalle({
                     titulo,
                     valor,
                     destacado = false,
                 }: {
    titulo: string;
    valor: string | number;
    destacado?: boolean;
}) {
    return (
        <div>
            <div
                style={{
                    fontSize: "0.75rem",
                    color: "#64748b",
                    marginBottom: "4px",
                    textTransform: "uppercase",
                    letterSpacing: "0.04em",
                }}
            >
                {titulo}
            </div>

            <div
                style={{
                    fontWeight: 700,
                    color: destacado ? "#15803d" : "#1e293b",
                }}
            >
                {valor}
            </div>
        </div>
    );
}