package org.example.thetest.service;

//import org.example.thetest.dto.UpdatePolizaDTO;
//import org.example.thetest.dto.CreatePolizaDTO;
import org.example.thetest.dto.PolizaDTO;
import org.example.thetest.dto.ReadAllClientesDTO;
import org.example.thetest.dto.ReadAllPolizasDTO;
import org.example.thetest.entity.Cliente;
import org.example.thetest.entity.Poliza;
import org.example.thetest.mapper.PolizaMapper;
//import org.example.thetest.repository.RolPantallaRepository;
import org.example.thetest.repository.PolizaRepository;
import org.example.thetest.repository.ClienteRepository;
//import org.example.thetest.repository.UsuarioPolizaRepository;
import org.example.thetest.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolizaService {
    public ClienteRepository clienteRepository;
    public PolizaRepository polizaRepository;
    //public final ClientePolizaRepository clientePolizaRepository;
    //private final RolPantallaRepository polizaPantallaRepository;
    private final PolizaMapper polizaMapper;

    public PolizaService(
            ClienteRepository clienteRepository,
            PolizaRepository polizaRepository,
            //ClientePolizaRepository clientePolizaRepository,
            //PolizaPantallaRepository polizaPantallaRepository,
            PolizaMapper polizaMapper
    )
    {
        this.clienteRepository = clienteRepository;
        this.polizaRepository=polizaRepository;
        //this.usuarioPolizaRepository = usuarioPolizaRepository;
        //this.rolPantallaRepository = rolPantallaRepository;
        this.polizaMapper=polizaMapper;
    }

    // ==================== Read All Polizas ====================

    public Page<ReadAllPolizasDTO> readAllPolizas(Pageable pageable){
        Page<Poliza> pagina=polizaRepository.findAll(pageable);
        return pagina.map(this::mapToDTO);
    }

    private ReadAllPolizasDTO mapToDTO(Poliza p) {

        ReadAllPolizasDTO dto = new ReadAllPolizasDTO();

        //Información mostrada (límites se establecen en conjunto con el DTO)
        dto.setIdPoliza(p.getIdPoliza());
        dto.setIdPoliza(p.getIdPoliza());
        dto.setNumeroPoliza(p.getNumeroPoliza());
        dto.setTipoSeguro(p.getTipoSeguro());
        dto.setEstadoSeguro(p.getEstadoSeguro());
        dto.setPrimaMensual(p.getPrimaMensual());
        dto.setCoberturaMensual(p.getCoberturaMensual());
        dto.setFechaInicio(p.getFechaInicio());
        dto.setFechaVencimiento(p.getFechaVencimiento());
        dto.setDescripcionPoliza(p.getDescripcionPoliza());

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
    //Crear poliza
    public PolizaDTO crearPoliza(CreatePolizaDTO dto){
        Poliza poliza=polizaMapper.toEntity(dto);

        Poliza guardado=polizaRepository.save(poliza);

        return polizaMapper.toDTO(guardado);
    }
     */

    /*
    //Actualizar poliza
    public PolizaDTO actualizarPoliza(Long id, ActualizarPolizaDTO dto){
        Poliza poliza=polizaRepository.findById(id).orElseThrow(() -> new RuntimeException("Poliza no encontrado"));

        poliza.setNombrePoliza(dto.getNombrePoliza());
        poliza.setDescripcionPoliza(dto.getDescripcionPoliza());

        Poliza actualizado=polizaRepository.save(poliza);

        return polizaMapper.toDTO(actualizado);
    }
     */

}

