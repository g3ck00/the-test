package org.example.thetest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Poliza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPoliza;

    @OneToMany(mappedBy="poliza")
    @JsonIgnore
    private List<ClientePoliza> clientePolizas;

    @NotBlank(message="El número de poliza es obligatorio...")
    private String numeroPoliza;

    @NotBlank(message="El tipo de seguro es obligatorio...")
    private String tipoSeguro;

    @NotBlank(message="El estado del seguro es obligatorio...")
    private String estadoSeguro;

    @NotBlank(message="La prima mensual es obligatoria...")
    private double primaMensual;

    @NotBlank(message="La cobertura mensual es obligatoria...")
    private double coberturaMensual;

    @NotBlank(message="La fecha de inicio es obligatoria...")
    private LocalDate fechaInicio;

    @NotBlank(message="La fecha de vencimiento es obligatoria...")
    private LocalDate fechaVencimiento;

    @NotBlank(message="La descripción es obligatoria...")
    private String descripcionPoliza;
}
