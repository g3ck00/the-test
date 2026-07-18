import http from "./http";
import type { CreatePolizaInput, Poliza } from "../types/poliza";
import type { PageResponse } from "../types/page";

// El backend no tiene un endpoint de estadísticas ni de "traer todo sin límite",
// así que pedimos una página bastante grande y trabajamos con ese listado.
export async function getPolizas(): Promise<Poliza[]> {
    const res = await http.get<PageResponse<Poliza>>("/polizas", {
        params: { page: 0, size: 200, sort: "idPoliza,desc" },
    });

    return res.data.content;
}

export async function crearPoliza(data: CreatePolizaInput): Promise<void> {
    await http.post("/polizas", data);
}

export async function eliminarPoliza(id: number): Promise<void> {
    await http.delete(`/polizas/${id}`);
}
