package org.example.thetest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PolizaDTO {
    private Long idPoliza;
    private String nombrePoliza;
    private String tipoSeguro;
    private String estadoSeguro;
    private double primaMensual;
    private double coberturaMensual;
    private LocalDate fechaInicio;
    private LocalDate fechaVencimiento;
    private String descripcionPoliza;
}