package org.example.thetest.controller;

import jakarta.validation.Valid;
import org.example.thetest.dto.CreatePolizaDTO;
import org.example.thetest.dto.ReadAllPolizasDTO;
import org.example.thetest.dto.ReadAllPolizasUpdatedDTO;
import org.example.thetest.dto.UpdatePolizaDTO;
import org.example.thetest.service.PolizaUpdatedService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import org.example.thetest.dto.UpdatePolizaDTO;
//import org.example.thetest.dto.CreatePolizaDTO;

@RestController
@RequestMapping("/polizas")
public class PolizaUpdatedController {

    private final PolizaUpdatedService polizaUpdatedService;

    public PolizaUpdatedController(PolizaUpdatedService polizaUpdatedService) {
        this.polizaUpdatedService = polizaUpdatedService;
    }

    // ==================== Read All Polizas ====================

    @GetMapping
    public Page<ReadAllPolizasUpdatedDTO> readAllPolizas(Pageable pageable){
        return polizaUpdatedService.readAllPolizas(pageable);
    }

    // ============================================================

    // ==================== Create Póliza ====================

    @PostMapping
    public ResponseEntity<String> crearPoliza(@Valid @RequestBody CreatePolizaDTO dto){
        polizaUpdatedService.crearPoliza(dto);
        return ResponseEntity.ok("Póliza añadida.");
    }

    // ============================================================

    // ==================== Update Póliza ====================

    @PutMapping("/{id}")
    public ResponseEntity<String> modificarPoliza(@PathVariable Long id, @Valid @RequestBody UpdatePolizaDTO dto) {
        polizaUpdatedService.modificarPoliza(id, dto);
        return ResponseEntity.ok("Póliza editada.");
    }

    // ============================================================

    // ==================== Delete Póliza ====================

    //CHANTAL
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPoliza(@PathVariable Long id) {
        polizaUpdatedService.eliminarPoliza(id);
        return ResponseEntity.ok("Póliza eliminada.");
    }

    // ============================================================
}
