export interface Poliza {
    idPoliza: number;
    codigoPoliza: string;
    nombreAsegurado: string;
    nombreBeneficiario: string;
    tipoSeguro: string;
    estadoSeguro: string;
    primaMensual: number;
    cobertura: number;
    fechaInicio: string; // yyyy-MM-dd
    fechaVencimiento: string; // yyyy-MM-dd
    descripcionPoliza: string;
    creadorPoliza: string | null;
    fechaCreacionPoliza: string | null;
    modificadorRegistroPoliza: string | null;
    fechaModificacionPoliza: string | null;
}

// Lo que el backend espera al crear una póliza (CreatePolizaDTO).
// estadoSeguro NO se envía: el backend lo pone en "Activa" automáticamente.
export interface CreatePolizaInput {
    codigoPoliza: string;
    nombreAsegurado: string;
    nombreBeneficiario: string;
    tipoSeguro: string;
    primaMensual: number;
    cobertura: number;
    fechaInicio: string;
    fechaVencimiento: string;
    descripcionPoliza: string;
}

export const TIPOS_SEGURO = ["Vida", "Salud", "Auto", "Hogar"] as const;
