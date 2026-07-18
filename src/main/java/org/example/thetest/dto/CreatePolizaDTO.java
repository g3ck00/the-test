package org.example.thetest.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreatePolizaDTO {

    //En realidad, debería generarse de manera automática
    @NotBlank
    private String codigoPoliza;

    @NotBlank
    private String nombreAsegurado;

    @NotBlank
    private String nombreBeneficiario;

    //Debería restringirse (a nivel de base de datos o de validación en Spring) a ciertas opciones
    @NotBlank
    private String tipoSeguro;

    //Debería restringirse (a nivel de base de datos o de validación de Spring) a solo "ACTIVA" e "VENCIDA"
    //@NotBlank
    //private String estadoSeguro;

    @NotNull
    private double primaMensual;

    @NotNull
    private double cobertura;

    //Debería estar restringido a solo tiempo presente
    @NotNull
    private LocalDate fechaInicio;

    @NotNull @FutureOrPresent
    private LocalDate fechaVencimiento;

    @NotBlank
    private String descripcionPoliza;
}