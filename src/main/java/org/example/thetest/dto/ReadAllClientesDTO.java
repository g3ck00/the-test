package org.example.thetest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReadAllClientesDTO {
    private Long idCliente;
    private String nombrePrimario;
    private String nombreSecundario;
    private String apellidoPrimario;
    private String apellidoSecundario;
    private Boolean estadoRegistro;
    private String creadorRegistro;
    private LocalDate fechaCreacion;
    private String modificadorRegistro;
    private LocalDate fechaModificacion;
}
