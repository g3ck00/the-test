package org.example.thetest.entity;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
public class IdClientePoliza implements java.io.Serializable {
    private Long idCliente;
    private Long idPoliza;

    public IdClientePoliza(){}

    public IdClientePoliza(Long idCliente, Long idPoliza){
        this.idCliente=idCliente;
        this.idPoliza=idPoliza;
    }
}
