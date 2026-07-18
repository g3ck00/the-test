import { useCallback, useEffect, useState } from "react";
import type { CreatePolizaInput, Poliza } from "../types/poliza.js";
import * as polizaService from "../services/polizaService.js";

export function usePolizas() {

    const [polizas, setPolizas] = useState<Poliza[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    const [pagina, setPagina] = useState(0);
    const [totalPaginas, setTotalPaginas] = useState(0);

    const cargarPolizas = useCallback(async () => {

        try {
            setLoading(true);
            setError(null);
            const data = await polizaService.getPolizas();
            setPolizas(data);
        } catch {
            setError("No se pudieron cargar las pólizas.");
        } finally {
            setLoading(false);
        }
    }, []);

    useEffect(() => {
        cargarPolizas();
    }, [cargarPolizas]);

    async function crearPoliza(data: CreatePolizaInput) {
        await polizaService.crearPoliza(data);
        await cargarPolizas();
    }

    async function eliminarPoliza(id: number) {
        await polizaService.eliminarPoliza(id);
        await cargarPolizas();
    }

    const activas = polizas.filter(
        (p) => p.estadoSeguro?.toLowerCase() === "activa"
    ).length;

    const primaTotalMensual = polizas.reduce(
        (acc, p) => acc + p.primaMensual,
        0
    );

    const coberturaTotal = polizas.reduce((acc, p) => acc + p.cobertura, 0);

    return {
        polizas,
        loading,
        error,
        total: polizas.length,
        activas,
        primaTotalMensual,
        coberturaTotal,
        crearPoliza,
        eliminarPoliza,
        recargar: cargarPolizas,
    };
}
