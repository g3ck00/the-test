import type { Poliza } from "../types/poliza.js";
import { formatDate, formatMoney } from "../utils/format.js";

interface PolizaCardProps {
    poliza: Poliza;
    onVerDetalles: (poliza: Poliza) => void;
}

export default function PolizaCard({ poliza, onVerDetalles }: PolizaCardProps) {
    const esActiva = poliza.estadoSeguro?.toLowerCase() === "activa";

    return (
        <div className="poliza-card">
            <div className="poliza-card-header">
                <span className="poliza-codigo">{poliza.codigoPoliza}</span>
                <span
                    className={`badge ${esActiva ? "badge-activa" : "badge-vencida"}`}
                >
                    {poliza.estadoSeguro}
                </span>
            </div>

            <p className="poliza-linea">
                <strong>Beneficiario:</strong> {poliza.nombreBeneficiario}
            </p>
            <p className="poliza-linea">
                <strong>Asegurado:</strong> {poliza.nombreAsegurado}
            </p>
            <p className="poliza-linea">
                <strong>Prima mensual:</strong> {formatMoney(poliza.primaMensual)}
            </p>
            <p className="poliza-linea">
                <strong>Inicio:</strong> {formatDate(poliza.fechaInicio)}
            </p>

            <button
                type="button"
                className="btn btn-secondary"
                onClick={() => onVerDetalles(poliza)}
            >
                Ver más detalles
            </button>
        </div>
    );
}
