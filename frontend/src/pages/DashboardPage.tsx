import { useState } from "react";
import { usePolizas } from "../hooks/usePolizas";
import StatCard from "../components/StatCard";
import PolizaCard from "../components/PolizaCard";
import PolizaFormModal from "../components/PolizaFormModal";
import PolizaDetailsModal from "../components/PolizaDetailsModal";
import type { Poliza } from "../types/poliza";
import { formatMoney } from "../utils/format";

export default function DashboardPage() {

    const {
        polizas,
        loading,
        error,
        total,
        activas,
        primaTotalMensual,
        coberturaTotal,
        crearPoliza,
        eliminarPoliza,
    } = usePolizas();

    const [isCreando, setIsCreando] = useState(false);
    const [polizaSeleccionada, setPolizaSeleccionada] = useState<Poliza | null>(
        null
    );

    return (
        <div className="page">
            <header className="page-header">
                <div>
                    <h1>Gestión de Seguros</h1>
                    <p className="subtitle">Administra tus pólizas de seguro.</p>
                </div>

                <div className="page-header-actions">
                    <span className="usuario-label">¡Hola!</span>
                </div>
            </header>

            <div className="toolbar">
                <button
                    type="button"
                    className="btn btn-primary"
                    onClick={() => setIsCreando(true)}
                >
                    + Crear Póliza
                </button>
            </div>

            <section className="stats-grid">
                <StatCard label="Total de pólizas" value={String(total)} />
                <StatCard label="Pólizas activas" value={String(activas)} />
                <StatCard
                    label="Prima total mensual"
                    value={formatMoney(primaTotalMensual)}
                />
                <StatCard label="Cobertura total" value={formatMoney(coberturaTotal)} />
            </section>

            {error && <p className="form-error">{error}</p>}

            {loading ? (
                <p className="estado-vacio">Cargando pólizas...</p>
            ) : polizas.length === 0 ? (
                <p className="estado-vacio">
                    Aún no tienes pólizas registradas. Crea la primera con el botón de
                    arriba.
                </p>
            ) : (
                <section className="polizas-grid">
                    {polizas.map((poliza) => (
                        <PolizaCard
                            key={poliza.idPoliza}
                            poliza={poliza}
                            onVerDetalles={setPolizaSeleccionada}
                        />
                    ))}
                </section>
            )}

            <PolizaFormModal
                isOpen={isCreando}
                onClose={() => setIsCreando(false)}
                onCrear={crearPoliza}
            />

            <PolizaDetailsModal
                poliza={polizaSeleccionada}
                onClose={() => setPolizaSeleccionada(null)}
                onEliminar={eliminarPoliza}
            />
        </div>
    );
}
