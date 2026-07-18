package org.example.thetest.service;

//import org.springdoc.core.converters.models.Pageable;
/*
import org.example.thetest.dto.ActualizarClienteDTO;
import org.example.thetest.dto.CrearClienteDTO;
import org.example.thetest.dto.LeerClientesDetalladosDTO;
import org.example.thetest.dto.ClienteDTO;
import org.example.thetest.entity.Cliente;
import org.example.thetest.mapper.ClienteMapper;
import org.example.thetest.repository.PolizaPantallaRepository;
import org.example.thetest.repository.ClienteRepository;
import org.example.thetest.repository.ClientePolizaRepository;
*/
import org.example.thetest.dto.ReadAllClientesDTO;
import org.example.thetest.entity.Cliente;
import org.example.thetest.mapper.ClienteMapper;
import org.example.thetest.repository.ClienteRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    //private final ClientePolizaRepository clientePolizaRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(
            ClienteRepository clienteRepository,
            //ClientePolizaRepository clientePolizaRepository,
            //PolizaPantallaRepository polizaPantallaRepository,
            ClienteMapper clienteMapper
        )
    {
        this.clienteRepository = clienteRepository;
        //this.clientePolizaRepository = clientePolizaRepository;
        //this.polizaPantallaRepository = polizaPantallaRepository;
        this.clienteMapper=clienteMapper;
    }

    /*
    public Cliente obtenerCliente(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }
     */

    // ==================== Read All Clientes ====================

    public Page<ReadAllClientesDTO> readAllClientes(Pageable pageable){
        Page<Cliente> pagina=clienteRepository.findAll(pageable);
        return pagina.map(this::mapToDTO);
    }

    private ReadAllClientesDTO mapToDTO(Cliente c) {

        ReadAllClientesDTO dto = new ReadAllClientesDTO();

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
        dto.setPolizas(u.getClientePolizaes()
                .stream()
                .filter(ur -> Boolean.TRUE.equals(ur.getActivo()))
                .map(ur -> ur.getPoliza().getNombrePoliza())
                .toList()
        );
         */

        return dto;
    }

    // ============================================================

    /*

    //Here comes the MAGIC!

    //Crear cliente
    public ClienteDTO crearCliente(CrearClienteDTO dto){
        Cliente cliente = mapper.toEntity(dto);

        cliente.setContrasenna(passwordEncoder.encode(dto.getContrasenna())); //Encriptar la contraseña luego de mapper

        //Extraer información de la sesión autenticada
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();

        //auth.getName() extrae el nombre de cliente autenticado en la sesión
        cliente.setCreadoPor(auth.getName());

        cliente.setFechaCreacionRegistrada(LocalDate.now());

        //System.out.println(passwordEncoder.encode("Admin123"));

        Cliente guardado = clienteRepository.save(cliente);

        return mapper.toDTO(guardado);
    }

    //Modificar cliente
    public ClienteDTO actualizarCliente(Long id, ActualizarClienteDTO dto) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.setNombreCliente(dto.getNombreCliente());
        cliente.setContrasenna(passwordEncoder.encode(dto.getContrasenna()));
        cliente.setEmail(dto.getEmail());
        cliente.setActivo(dto.getActivo());

        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        cliente.setModificadoPor(auth.getName());
        cliente.setFechaModificacion(LocalDate.now());

        Cliente actualizado = clienteRepository.save(cliente);

        return mapper.toDTO(actualizado);
    }

    // #################### JPQL ####################

    //Read todos los clientes (JPQL)
    public List<Cliente> readClientesJPQL(){
        return clienteRepository.readClientesJPQL();
    }

    //Read todos los clientes (JPQL, detallados)
    public Page<LeerClientesDetalladosDTO> readClientesJPQLDetallados(Pageable pageable){
        Page<Cliente> pagina=clienteRepository.readClientesJPQLDetallados(pageable);
        return pagina.map(this::mapToDetalladoDTO);
    }

    //Read todos los clientes where estado del registro = false
    public Page<LeerClientesDetalladosDTO> readClientesJPQLConEstadoDelRegistroInactivo(Pageable pageable) {
        Page<Cliente> pagina=clienteRepository.readClientesJPQLConEstadoDelRegistroInactivo(pageable);
        return pagina.map(this::mapToDetalladoDTO);
    }

    //Read todos los clientes where ID>=10 and ID<=20
    public Page<LeerClientesDetalladosDTO> readClientesJPQLConIDEntreDiezYVeinteInclusivos(Pageable pageable) {
        Page<Cliente> pagina=clienteRepository.readClientesJPQLConIDEntreDiezYVeinteInclusivos(pageable);
        return pagina.map(this::mapToDetalladoDTO);
    }

    //Read todos los clientes where activo...?
    public Page<LeerClientesDetalladosDTO> readClientesActivosOInactivos(Pageable pageable)
    {
        Page<Cliente> pagina=clienteRepository.readClientesActivosOInactivos(pageable);
        return pagina.map(this::mapToDetalladoDTO);
    }

    // #################### Query nativos ####################

    //...
     */
}