package org.example.thetest.controller;

import jakarta.validation.Valid;
import org.example.thetest.dto.ReadAllClientesDTO;
import org.example.thetest.dto.ReadAllPolizasDTO;
import org.example.thetest.service.PolizaService;
//import org.example.thetest.dto.UpdatePolizaDTO;
//import org.example.thetest.dto.CreatePolizaDTO;
import org.example.thetest.dto.PolizaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polizas")
public class PolizaController {

    private final PolizaService polizaService;

    public PolizaController(PolizaService polizaService) {
        this.polizaService = polizaService;
    }

    @GetMapping
    public Page<ReadAllPolizasDTO> readAllPolizasDTOS(Pageable pageable){
        return polizaService.readAllPolizas(pageable);
    }

    /*
    //Leer roles
    @GetMapping
    public List<RolDTO> leerRoles(){
        return polizaService.leerRoles();
    }

    //Crear rol
    @PostMapping
    public ResponseEntity<String> crearRol(@Valid @RequestBody CrearRolDTO dto){
        polizaService.crearRol(dto);
        return ResponseEntity.ok().body("Rol creado.");
    }

    //Actualizar rol
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarRol(@PathVariable Long id, @Valid @RequestBody ActualizarRolDTO dto){
        polizaService.actualizarRol(id, dto);
        return ResponseEntity.ok().body("Rol actualizado.");
    }
     */
}
