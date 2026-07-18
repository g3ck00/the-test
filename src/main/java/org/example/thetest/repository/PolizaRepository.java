package org.example.thetest.repository;

import org.example.thetest.entity.Poliza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PolizaRepository extends JpaRepository<Poliza, Long> {
    //Read All Pólizas
    @Query("""
        select p 
        from Poliza p
        """)
    Page<Poliza> readAllPolizas(Pageable pageable);
}