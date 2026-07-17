package org.example.thetest.entity;

import java.time.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<ClientePoliza> clientePolizas;

    @NotBlank(message="El nombre primario es obligatorio...")
    private String nombrePrimario;

    @NotBlank(message="El nombre secundario es obligatorio...")
    private String nombreSecundario;

    @NotBlank(message="El apellido primario es obligatorio...")
    private String apellidoPrimario;

    @NotBlank(message="El apellido secundario es obligatorio...")
    private String apellidoSecundario;

    @NotNull(message="El estado del registro es obligatorio...")
    private Boolean estadoRegistro;

    private String creadorRegistro;
    private LocalDate fechaCreacion;

    private String modificadorRegistro;
    private LocalDate fechaModificacion;
}
