package org.example.thetest.repository;

import org.example.thetest.entity.PolizaUpdated;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PolizaUpdatedRepository extends JpaRepository<PolizaUpdated, Long> {
    //Read All Pólizas
    @Query("""
        select p 
        from PolizaUpdated p
        """)
    Page<PolizaUpdated> readAllPolizas(Pageable pageable);
}