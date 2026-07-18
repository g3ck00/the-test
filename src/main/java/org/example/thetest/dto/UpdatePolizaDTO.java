package org.example.thetest.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdatePolizaDTO {
    private String nombreAsegurado;

    private String nombreBeneficiario;

    //Debería restringirse (a nivel de base de datos o de validación en Spring) a ciertas opciones
    private String tipoSeguro;

    //Debería restringirse (a nivel de base de datos o de validación de Spring) a solo "ACTIVA" e "VENCIDA"
    private String estadoSeguro;

    private Double primaMensual;

    private Double cobertura;

    //Debería estar restringido a solo tiempo presente
    private LocalDate fechaInicio;

    private LocalDate fechaVencimiento;

    private String descripcionPoliza;
}
