package org.example.thetest.repository;

import org.example.thetest.entity.Usuario;
import org.example.thetest.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    //Read todos los usuarios (JPQL, detallados)
    @Query("""
        select u 
        from Usuario u
        """)
    Page<Cliente> readClientesJPQLDetallados(Pageable pageable);
}