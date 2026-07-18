package org.example.thetest.service;

import org.example.thetest.dto.ReadAllClientePolizaDTO;
import org.example.thetest.entity.IdClientePoliza;
//import org.example.thetest.dto.CreateClientePolizaDTO;
//import org.example.thetest.dto.DeleteClientePolizaDTO;
import org.example.thetest.dto.ReadAllClientePolizaDTO;
import org.example.thetest.entity.Poliza;
import org.example.thetest.entity.Cliente;
import org.example.thetest.entity.ClientePoliza;
import org.example.thetest.mapper.ClientePolizaMapper;
//import org.example.thetest.repository.RolPantallaRepository;
import org.example.thetest.repository.PolizaRepository;
import org.example.thetest.repository.ClienteRepository;
import org.example.thetest.repository.ClientePolizaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Service
public class ClientePolizaService {
    private final ClienteRepository clienteRepository;
    private final ClientePolizaRepository clientePolizaRepository;
    //private final RolPantallaRepository rolPantallaRepository;
    private final ClientePolizaMapper clientePolizaMapper;
    private final PolizaRepository rolRepository;

    public ClientePolizaService(
            ClienteRepository clienteRepository,
            ClientePolizaRepository clientePolizaRepository,
            //RolPantallaRepository rolPantallaRepository,
            ClientePolizaMapper usuarioRolMapper,
            PolizaRepository rolRepository) {
        this.clienteRepository = clienteRepository;
        this.clientePolizaRepository = clientePolizaRepository;
        //this.rolPantallaRepository = rolPantallaRepository;
        this.clientePolizaMapper = usuarioRolMapper;
        this.rolRepository = rolRepository;
    }

    //Leer roles asignados
    public List<ReadAllClientePolizaDTO> readPolizasAsignadas(){
        return clientePolizaRepository.findAll().stream().map(clientePolizaMapper::toDTO).toList();
    }

    /*

    //Crear ClientePoliza
    public ClientePolizaDTO crearUsuarioRol(CrearUsuarioRolDTO dto){

        Cliente usuario=clienteRepository.findById(dto.getIdUsuario()).orElseThrow(()->new RuntimeException("Cliente no encontrado..."));

        Poliza poliza = rolRepository.findById(dto.getIdRol()).orElseThrow(()->new RuntimeException("Poliza no encontrado..."));

        ClientePoliza usuarioRol=new ClientePoliza();

        IdClientePoliza id=new IdClientePoliza();
        id.setUsuarioId(usuario.getIdUsuario());
        id.setRolId(poliza.getIdRol());

        usuarioRol.setId(id);
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(poliza);
        usuarioRol.setFechaAsignacion(dto.getFechaAsignacion());
        usuarioRol.setActivo(dto.getActivo());

        ClientePoliza guardado=usuarioRolRepository.save(usuarioRol);

        return usuarioRolMapper.toDTO(guardado);

        /*ClientePoliza usuarioRol = usuarioRolMapper.toEntity(dto);

        ClientePoliza guardado=usuarioRolRepository.save(usuarioRol);

        return  usuarioRolMapper.toDTO(guardado);
    }

    //Eliminar poliza asignado a usuario
    @DeleteMapping
    public ClientePolizaDTO eliminarUsuarioRol(EliminarUsuarioRolDTO dto){
        IdClientePoliza id=new IdClientePoliza();

        id.setUsuarioId(dto.getIdUsuario());
        id.setRolId(dto.getIdRol());

        if (!usuarioRolRepository.existsById(id)){
            throw new RuntimeException(("El usuario no tiene ese poliza..."));
        }

        usuarioRolRepository.deleteById(id);
        return null;
    }
    */
}
