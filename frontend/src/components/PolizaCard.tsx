import type { Poliza } from "../types/poliza.js";
import { formatDate, formatMoney } from "../utils/format.js";

interface PolizaCardProps {
    poliza: Poliza;
    onVerDetalles: (poliza: Poliza) => void;
}

export default function PolizaCard({ poliza, onVerDetalles }: PolizaCardProps) {
    const esActiva = poliza.estadoSeguro?.toLowerCase() === "activa";

    return (
        <div
            style={{
                background: "#ffffff",
                borderRadius: "16px",
                padding: "20px",
                boxShadow: "0 4px 14px rgba(0,0,0,0.08)",
                border: "1px solid #e5e7eb",
                display: "flex",
                flexDirection: "column",
                gap: "14px",
                transition: "transform 0.2s ease, box-shadow 0.2s ease",
            }}
        >
            <div
                style={{
                    display: "flex",
                    justifyContent: "space-between",
                    alignItems: "center",
                    paddingBottom: "12px",
                    borderBottom: "1px solid #f1f5f9",
                }}
            >
                <span
                    style={{
                        fontSize: "1.1rem",
                        fontWeight: 700,
                        color: "#1e293b",
                    }}
                >
                    {poliza.codigoPoliza}
                </span>

                <span
                    style={{
                        padding: "5px 12px",
                        borderRadius: "999px",
                        fontSize: "0.8rem",
                        fontWeight: 600,
                        backgroundColor: esActiva ? "#dcfce7" : "#fee2e2",
                        color: esActiva ? "#166534" : "#991b1b",
                    }}
                >
                    {poliza.estadoSeguro}
                </span>
            </div>

            <div
                style={{
                    display: "flex",
                    flexDirection: "column",
                    gap: "8px",
                    color: "#475569",
                    fontSize: "0.95rem",
                }}
            >
                <p style={{ margin: 0 }}>
                    <strong style={{ color: "#1e293b" }}>Beneficiario:</strong>{" "}
                    {poliza.nombreBeneficiario}
                </p>

                <p style={{ margin: 0 }}>
                    <strong style={{ color: "#1e293b" }}>Asegurado:</strong>{" "}
                    {poliza.nombreAsegurado}
                </p>

                <p style={{ margin: 0 }}>
                    <strong style={{ color: "#1e293b" }}>Prima mensual:</strong>{" "}
                    {formatMoney(poliza.primaMensual)}
                </p>

                <p style={{ margin: 0 }}>
                    <strong style={{ color: "#1e293b" }}>Inicio:</strong>{" "}
                    {formatDate(poliza.fechaInicio)}
                </p>
            </div>

            <button
                type="button"
                onClick={() => onVerDetalles(poliza)}
                style={{
                    marginTop: "8px",
                    background: "#2563eb",
                    color: "#ffffff",
                    border: "none",
                    borderRadius: "10px",
                    padding: "10px 16px",
                    fontSize: "0.95rem",
                    fontWeight: 600,
                    cursor: "pointer",
                    transition: "background 0.2s ease",
                }}
                onMouseEnter={(e) => {
                    e.currentTarget.style.background = "#1d4ed8";
                }}
                onMouseLeave={(e) => {
                    e.currentTarget.style.background = "#2563eb";
                }}
            >
                Ver más detalles
            </button>
        </div>
    );
}