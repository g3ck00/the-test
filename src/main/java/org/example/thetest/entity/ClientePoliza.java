package org.example.thetest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class ClientePoliza {
    @EmbeddedId
    private IdClientePoliza id;

    @ManyToOne
    @MapsId("idCliente")
    @JoinColumn(name="id_cliente")
    @JsonIgnore
    private Cliente cliente;

    @ManyToOne
    @MapsId("idPoliza")
    @JoinColumn(name="id_poliza")
    private Poliza poliza;

    @NotNull(message="La fecha de asignación es obligatoria...")
    private LocalDate fechaAsignacion;

    @NotNull(message="El estado del registro es obligatorio (true/false).")
    private Boolean estadoRegistro;
}
