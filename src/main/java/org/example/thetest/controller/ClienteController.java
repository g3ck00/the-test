package org.example.thetest.contpolizaler;

import org.example.thetest.dto.ReadAllClientesDTO;
import org.example.thetest.service.ClienteService;
//import org.example.thetest.dto.ActualizarClienteDTO;
//import org.example.thetest.dto.CrearClienteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public Page<ReadAllClientesDTO> readAllClientes(Pageable pageable){
        return clienteService.readAllClientes(pageable);
    }

    /*
    //Leer clientes v2
    @GetMapping()
    public List<LeerClientesDetalladosDTO> leerClientesDetalladosDTOS(){
        return clienteService.leerClientesDetalladosDTO();
    }
     */

    /*
    //Crear cliente
    @PostMapping
    public ResponseEntity<String> crearCliente(@Valid @RequestBody CrearClienteDTO dto){
        clienteService.crearCliente(dto);
        return ResponseEntity.ok("Cliente añadido.");
    }

    //Modificar cliente
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarCliente(@PathVariable Long id, @Valid @RequestBody ActualizarClienteDTO dto) {
        clienteService.actualizarCliente(id, dto);
        return ResponseEntity.ok("Cliente editado.");
    }

    //Leer cliente por ID
    @GetMapping("/{id}")
    public Cliente obtenerCliente(@PathVariable Long id) {
        return clienteService.obtenerCliente(id);
    }

    // #################### JPQL ####################

    //Endpoint global para JPQL
    // ...

    //Read todos los clientes (JPQL)
    @GetMapping("/jpql")
    public List<Cliente> readClientesJPQL() {
        return clienteService.readClientesJPQL();
    }

    //Read todos los clientes (JPQL, detallados)
    @GetMapping("/jpql/detallados")
    public Page<LeerClientesDetalladosDTO> readClientesJPQLDetallados(Pageable pageable) {
        return clienteService.readClientesJPQLDetallados(pageable);
    }

    //Read todos los clientes where estado del registro = false
    @GetMapping("/jpql/clientes/registros-inactivos")
    public Page<LeerClientesDetalladosDTO> readClientesJPQLConEstadoDelRegistroInactivo(Pageable pageable) {
        return clienteService.readClientesJPQLConEstadoDelRegistroInactivo(pageable);
    }

    //Read todos los clientes where ID>=10 and ID<=20
    @GetMapping("/jpql/clientes/id-entre-10-y-20-inclusivos")
    public Page<LeerClientesDetalladosDTO> readClientesJPQLConIDEntreDiezYVeinteInclusivos(Pageable pageable) {
        return clienteService.readClientesJPQLConIDEntreDiezYVeinteInclusivos(pageable);
    }

    //
    @GetMapping("/jpql/clientes/activos-o-inactivos")
    public Page<LeerClientesDetalladosDTO> readClientesActivosOInactivos(Pageable pageable)
    {
        return clienteService.readClientesJPQLConIDEntreDiezYVeinteInclusivos(pageable);
    }
     */

}