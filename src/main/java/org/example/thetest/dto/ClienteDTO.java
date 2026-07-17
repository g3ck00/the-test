package org.example.thetest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ClienteDTO {
    private Long idCliente;
    private String nombrePrimario;
    private String nombreSecundario;
    private String apellidoPrimario;
    private String apellidoSecundario;
}
