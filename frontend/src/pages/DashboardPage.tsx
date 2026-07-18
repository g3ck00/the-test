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
        pagina,
        setPagina,
        totalPaginas,
    } = usePolizas();

    const [isCreando, setIsCreando] = useState(false);
    const [polizaSeleccionada, setPolizaSeleccionada] =
        useState<Poliza | null>(null);

    return (
        <div
            style={{
                minHeight: "100vh",
                background: "#f1f5f9",
                padding: "24px",
                fontFamily: "Arial, sans-serif",
            }}
        >
            <header
                style={{
                    background: "#ffffff",
                    padding: "24px",
                    borderRadius: "12px",
                    border: "1px solid #e2e8f0",
                    marginBottom: "24px",
                    display: "flex",
                    justifyContent: "space-between",
                    alignItems: "center",
                }}
            >
                <div>
                    <h1
                        style={{
                            margin: 0,
                            color: "#1e293b",
                            fontSize: "1.8rem",
                        }}
                    >
                        theTest - Sistema de Administración de Seguros
                    </h1>

                    <p
                        style={{
                            margin: "8px 0 0",
                            color: "#64748b",
                        }}
                    >
                        It works!
                    </p>
                </div>

                <button
                    onClick={() => setIsCreando(true)}
                    style={{
                        background: "#2563eb",
                        color: "white",
                        border: "none",
                        borderRadius: "8px",
                        padding: "10px 18px",
                        cursor: "pointer",
                        fontWeight: 600,
                    }}
                >
                    Crear Póliza
                </button>
            </header>

            <section
                style={{
                    display: "grid",
                    gridTemplateColumns:
                        "repeat(auto-fit, minmax(200px, 1fr))",
                    gap: "16px",
                    marginBottom: "24px",
                }}
            >
                <StatCard
                    label="Total de pólizas"
                    value={String(total)}
                />

                <StatCard
                    label="Pólizas activas"
                    value={String(activas)}
                />

                <StatCard
                    label="Prima mensual"
                    value={formatMoney(primaTotalMensual)}
                />

                <StatCard
                    label="Cobertura total"
                    value={formatMoney(coberturaTotal)}
                />

            </section>

            {error && (
                <div
                    style={{
                        background: "#fee2e2",
                        color: "#991b1b",
                        padding: "12px",
                        borderRadius: "8px",
                        marginBottom: "20px",
                    }}
                >
                    {error}
                </div>
            )}

            <section>
                <h2
                    style={{
                        color: "#334155",
                        fontSize: "1.3rem",
                        marginBottom: "16px",
                    }}
                >
                    Pólizas
                </h2>

                {loading ? (
                    <div
                        style={{
                            background: "white",
                            padding: "20px",
                            borderRadius: "10px",
                            textAlign: "center",
                        }}
                    >
                        Cargando pólizas...
                    </div>
                ) : polizas.length === 0 ? (
                    <div
                        style={{
                            background: "white",
                            padding: "20px",
                            borderRadius: "10px",
                            textAlign: "center",
                            color: "#64748b",
                        }}
                    >
                        No hay pólizas registradas.
                    </div>
                ) : (
                    <div
                        style={{
                            display: "grid",
                            gridTemplateColumns:
                                "repeat(auto-fill, minmax(300px, 1fr))",
                            gap: "16px",
                        }}
                    >
                        {polizas.map((poliza) => (
                            <PolizaCard
                                key={poliza.idPoliza}
                                poliza={poliza}
                                onVerDetalles={setPolizaSeleccionada}
                            />
                        ))}
                    </div>
                )}

                <button disabled={pagina===0}
                        onClick={()=>setPagina(pagina-1)}
                >Anterior</button>

                <span>Pagina {pagina+1} de {totalPaginas}</span>

                <button disabled={pagina+1>=totalPaginas}
                        onClick={()=>setPagina(pagina+1)}
                >Siguiente</button>

            </section>

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