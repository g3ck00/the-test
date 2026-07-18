package org.example.thetest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PolizaUpdated {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPoliza;

    @NotBlank(message="El número de poliza es obligatorio.")
    private String codigoPoliza;

    @NotBlank(message="El nombre del asegurado es obligatorio.")
    private String nombreAsegurado;

    @NotBlank(message="El nombre del beneficiario es obligatorio.")
    private String nombreBeneficiario;

    @NotBlank(message="El tipo de seguro es obligatorio.")
    private String tipoSeguro;

    @NotBlank(message="El estado del seguro es obligatorio.")
    private String estadoSeguro;

    @NotNull(message="La prima mensual es obligatoria.")
    private double primaMensual;

    @NotNull(message="La cobertura es obligatoria.")
    private double cobertura;

    @NotNull(message="La fecha de inicio es obligatoria...")
    private LocalDate fechaInicio;

    @NotNull(message="La fecha de vencimiento es obligatoria.")
    private LocalDate fechaVencimiento;

    @NotBlank(message="La descripción es obligatoria.")
    private String descripcionPoliza;

    //Campos de auditoría

    private String creadorPoliza;
    private LocalDate fechaCreacionPoliza;

    private String modificadorRegistroPoliza;
    private LocalDate fechaModificacionPoliza;
}
