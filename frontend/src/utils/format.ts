export function formatMoney(value: number): string {
    return new Intl.NumberFormat("es-EC", {
        style: "currency",
        currency: "USD",
    }).format(value);
}

export function formatDate(value: string | null): string {
    if (!value) return "-";

    const [anio, mes, dia] = value.split("-");

    if (!anio || !mes || !dia) return value;

    return `${dia}/${mes}/${anio}`;
}
