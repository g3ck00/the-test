package org.example.thetest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PolizaUpdatedDTO {
    private Long idPoliza;
    private String codigoPoliza;
    private String nombreAsegurado;
    private String nombreBeneficiario;
    private String tipoSeguro;
    private String estadoSeguro;
    private double primaMensual;
    private double cobertura;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento;
    private String descripcionPoliza;

    //Campos de auditoría

    private String creadorPoliza;
    private LocalDate fechaCreacionPoliza;

    private String modificadorRegistroPoliza;
    private LocalDate fechaModificacionPoliza;
}