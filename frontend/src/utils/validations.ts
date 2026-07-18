import { z } from "zod";

// Refleja las validaciones del backend (CreatePolizaDTO)
export const polizaSchema = z.object({
    codigoPoliza: z.string().min(1, "El código de póliza es obligatorio."),
    nombreAsegurado: z.string().min(1, "El nombre del asegurado es obligatorio."),
    nombreBeneficiario: z
        .string()
        .min(1, "El nombre del beneficiario es obligatorio."),
    tipoSeguro: z.string().min(1, "Selecciona un tipo de seguro."),
    primaMensual: z
        .number("La prima mensual es obligatoria.")
        .positive("La prima mensual debe ser mayor a 0."),
    cobertura: z
        .number("La cobertura es obligatoria.")
        .positive("La cobertura debe ser mayor a 0."),
    fechaInicio: z.string().min(1, "La fecha de inicio es obligatoria."),
    fechaVencimiento: z
        .string()
        .min(1, "La fecha de vencimiento es obligatoria."),
    descripcionPoliza: z.string().min(1, "La descripción es obligatoria."),
});

export type PolizaFormData = z.infer<typeof polizaSchema>;
