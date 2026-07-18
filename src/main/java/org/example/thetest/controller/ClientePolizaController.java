package org.example.thetest.controller;

import jakarta.validation.Valid;
import org.example.thetest.dto.ReadAllClientePolizaDTO;
import org.example.thetest.dto.ReadAllClientesDTO;
import org.example.thetest.entity.Cliente;
import org.example.thetest.entity.ClientePoliza;
import org.example.thetest.repository.ClientePolizaRepository;
import org.example.thetest.service.ClientePolizaService;
//import org.example.thetest.dto.CreateClienteRolDTO;
//import org.example.thetest.dto.DeleteClientePolizaDTO;
import org.example.thetest.dto.ReadAllPolizasDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polizas-asignadas")
public class ClientePolizaController{
    private final ClientePolizaService clientePolizaService;

    private final ClientePolizaRepository clientePolizaRepository;

    public ClientePolizaController(ClientePolizaService clientePolizaService, ClientePolizaRepository clientePolizaRepository) {
        this.clientePolizaService = clientePolizaService;
        this.clientePolizaRepository = clientePolizaRepository;
    }

    // ==================== Read All Clientes y sus Polizas Asignadas ====================

    //Sin paginación

    @GetMapping
    public List<ReadAllClientePolizaDTO> readPolizasAsignadas(){
        return clientePolizaService.readPolizasAsignadas();
    }

    // ============================================================

    /*
    //Leer roles asignados
    @GetMapping
    public List<UsuarioRolDTO> leerRolesAsignados(){
        return usuarioRolService.leerRolesAsignados();
    }

    /*
    //Crear UsuarioRol
    @PostMapping
    public ResponseEntity<String> crearUsuarioRol(@Valid @RequestBody CrearUsuarioRolDTO dto){
        usuarioRolService.crearUsuarioRol(dto);
        return ResponseEntity.ok().body("Asignación de rol completada.");
    }

    //Eliminar rol asignado a usuario
    @DeleteMapping
    public ResponseEntity<String> eliminarRolAsignado(@Valid @RequestBody EliminarUsuarioRolDTO dto){
        usuarioRolService.eliminarUsuarioRol(dto);

        return ResponseEntity.ok("Rol eliminado.");
    }
     */
}
