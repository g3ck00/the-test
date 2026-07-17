package org.example.thetest.service;

//import org.springdoc.core.converters.models.Pageable;
import org.example.projecttwo.dto.ActualizarUsuarioDTO;
import org.example.projecttwo.dto.CrearUsuarioDTO;
import org.example.projecttwo.dto.LeerUsuariosDetalladosDTO;
import org.example.projecttwo.dto.UsuarioDTO;
import org.example.projecttwo.entity.Usuario;
import org.example.projecttwo.mapper.UsuarioMapper;
import org.example.projecttwo.repository.RolPantallaRepository;
import org.example.projecttwo.repository.UsuarioRepository;
import org.example.projecttwo.repository.UsuarioRolRepository;
import org.example.thetest.dto.ReadClientesDTO;
import org.example.thetest.entity.Cliente;
import org.example.thetest.mapper.ClienteMapper;
import org.example.thetest.repository.ClienteRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    //private final ClientePolizaRepository usuarioRolRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(
            ClienteRepository clienteRepository,
            //ClientePolizaRepository clientePolizaRepository,
            //RolPantallaRepository rolPantallaRepository,
            ClienteMapper mapper,
        )
    {
        this.clienteRepository = clienteRepository;
        //this.clientePolizaRepository = clientePolizaRepository;
        //this.rolPantallaRepository = rolPantallaRepository;
        this.clienteMapper=clienteMapper;
    }

    /*
    public Cliente obtenerUsuario(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
     */

    // ==================== Read All Clientes ====================

    public Page<ReadClientesDTO> readAllClientes(Pageable pageable){
        Page<Cliente> pagina=clienteRepository.findAll(pageable);
        return pagina.map(this::mapToDTO);
    }

    private ReadClientesDTO mapToDTO(Cliente c) {

        ReadClientesDTO dto = new ReadClientesDTO();

        //Información mostrada (límites se establecen en conjunto con el DTO)
        dto.setIdCliente(c.getIdCliente());
        dto.setNombrePrimario(c.getNombrePrimario());
        dto.setNombreSecundario(c.getNombreSecundario());
        dto.setApellidoPrimario(c.getApellidoPrimario());
        dto.setApellidoSecundario(c.getApellidoSecundario());
        dto.setEstadoRegistro(c.getEstadoRegistro());
        dto.setCreadorRegistro(c.getCreadorRegistro());
        dto.setFechaCreacion(c.getFechaCreacion());
        dto.setModificadorRegistro(c.getModificadorRegistro());
        dto.setFechaModificacion(c.getFechaModificacion());

        /*
        dto.setPolizas(u.getUsuarioRoles()
                .stream()
                .filter(ur -> Boolean.TRUE.equals(ur.getActivo()))
                .map(ur -> ur.getRol().getNombreRol())
                .toList()
        );
         */

        return dto;
    }

    //Here comes the MAGIC!

    //Crear usuario
    public UsuarioDTO crearUsuario(CrearUsuarioDTO dto){
        Usuario usuario = mapper.toEntity(dto);

        usuario.setContrasenna(passwordEncoder.encode(dto.getContrasenna())); //Encriptar la contraseña luego de mapper

        //Extraer información de la sesión autenticada
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();

        //auth.getName() extrae el nombre de usuario autenticado en la sesión
        usuario.setCreadoPor(auth.getName());

        usuario.setFechaCreacionRegistrada(LocalDate.now());

        //System.out.println(passwordEncoder.encode("Admin123"));

        Usuario guardado = usuarioRepository.save(usuario);

        return mapper.toDTO(guardado);
    }

    //Modificar usuario
    public UsuarioDTO actualizarUsuario(Long id, ActualizarUsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setContrasenna(passwordEncoder.encode(dto.getContrasenna()));
        usuario.setEmail(dto.getEmail());
        usuario.setActivo(dto.getActivo());

        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        usuario.setModificadoPor(auth.getName());
        usuario.setFechaModificacion(LocalDate.now());

        Usuario actualizado = usuarioRepository.save(usuario);

        return mapper.toDTO(actualizado);
    }

    // #################### JPQL ####################

    //Read todos los usuarios (JPQL)
    public List<Usuario> readUsuariosJPQL(){
        return usuarioRepository.readUsuariosJPQL();
    }

    //Read todos los usuarios (JPQL, detallados)
    public Page<LeerUsuariosDetalladosDTO> readUsuariosJPQLDetallados(Pageable pageable){
        Page<Usuario> pagina=usuarioRepository.readUsuariosJPQLDetallados(pageable);
        return pagina.map(this::mapToDetalladoDTO);
    }

    //Read todos los usuarios where estado del registro = false
    public Page<LeerUsuariosDetalladosDTO> readUsuariosJPQLConEstadoDelRegistroInactivo(Pageable pageable) {
        Page<Usuario> pagina=usuarioRepository.readUsuariosJPQLConEstadoDelRegistroInactivo(pageable);
        return pagina.map(this::mapToDetalladoDTO);
    }

    //Read todos los usuarios where ID>=10 and ID<=20
    public Page<LeerUsuariosDetalladosDTO> readUsuariosJPQLConIDEntreDiezYVeinteInclusivos(Pageable pageable) {
        Page<Usuario> pagina=usuarioRepository.readUsuariosJPQLConIDEntreDiezYVeinteInclusivos(pageable);
        return pagina.map(this::mapToDetalladoDTO);
    }

    //Read todos los usuarios where activo...?
    public Page<LeerUsuariosDetalladosDTO> readUsuariosActivosOInactivos(Pageable pageable)
    {
        Page<Usuario> pagina=usuarioRepository.readUsuariosActivosOInactivos(pageable);
        return pagina.map(this::mapToDetalladoDTO);
    }

    // #################### Query nativos ####################

    //...
}