package org.example.thetest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReadAllClientePolizaDTO {
    private Long idCliente;

    private Long idPoliza;

    private LocalDate fechaAsignacion;

    private Boolean estadoRegistro;
}
