package org.example.thetest.repository;

import org.example.thetest.entity.Cliente;
import org.example.thetest.entity.IdClientePoliza;
import org.example.thetest.entity.ClientePoliza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientePolizaRepository extends JpaRepository<ClientePoliza, IdClientePoliza> {

    List<ClientePoliza> findByCliente_IdCliente(Long idCliente);

    List<ClientePoliza> findByPoliza_IdPoliza(Long idPoliza);

    List<ClientePoliza> findByCliente_IdClienteAndEstadoRegistroTrue(Long idCliente);
}