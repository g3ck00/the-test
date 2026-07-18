package org.example.thetest.service;

//import org.example.thetest.dto.UpdatePolizaDTO;
//import org.example.thetest.dto.CreatePolizaDTO;

import org.example.thetest.dto.*;
import org.example.thetest.entity.PolizaUpdated;
import org.example.thetest.mapper.PolizaUpdatedMapper;
import org.example.thetest.repository.PolizaUpdatedRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

//import org.example.thetest.repository.RolPantallaRepository;
//import org.example.thetest.repository.UsuarioPolizaRepository;

@Service
public class PolizaUpdatedService {
    public PolizaUpdatedRepository polizaUpdatedRepository;
    //public final ClientePolizaRepository clientePolizaRepository;
    //private final RolPantallaRepository polizaPantallaRepository;
    private final PolizaUpdatedMapper polizaUpdatedMapper;

    public PolizaUpdatedService(
            PolizaUpdatedRepository polizaUpdatedRepository,
            //ClientePolizaRepository clientePolizaRepository,
            //PolizaPantallaRepository polizaPantallaRepository,
            PolizaUpdatedMapper polizaUpdatedMapper
    )
    {
        this.polizaUpdatedRepository=polizaUpdatedRepository;
        //this.usuarioPolizaRepository = usuarioPolizaRepository;
        //this.rolPantallaRepository = rolPantallaRepository;
        this.polizaUpdatedMapper=polizaUpdatedMapper;
    }

    // ==================== Read All Polizas ====================

    public Page<ReadAllPolizasUpdatedDTO> readAllPolizas(Pageable pageable){
        Page<PolizaUpdated> pagina=polizaUpdatedRepository.findAll(pageable);
        return pagina.map(this::mapToDTO);
    }

    private ReadAllPolizasUpdatedDTO mapToDTO(PolizaUpdated p) {

        ReadAllPolizasUpdatedDTO dto = new ReadAllPolizasUpdatedDTO();

        //Información mostrada (límites se establecen en conjunto con el DTO)
        dto.setIdPoliza(p.getIdPoliza());
        dto.setCodigoPoliza(p.getCodigoPoliza());
        dto.setNombreAsegurado(p.getNombreAsegurado());
        dto.setNombreBeneficiario(p.getNombreBeneficiario());
        dto.setTipoSeguro(p.getTipoSeguro());
        dto.setEstadoSeguro(p.getEstadoSeguro());
        dto.setPrimaMensual(p.getPrimaMensual());
        dto.setCobertura(p.getCobertura());
        dto.setFechaInicio(p.getFechaInicio());
        dto.setFechaVencimiento(p.getFechaVencimiento());
        dto.setDescripcionPoliza(p.getDescripcionPoliza());
        dto.setCreadorPoliza(p.getCreadorPoliza());
        dto.setFechaCreacionPoliza(p.getFechaCreacionPoliza());
        dto.setModificadorRegistroPoliza(p.getModificadorRegistroPoliza());
        dto.setFechaModificacionPoliza(p.getFechaModificacionPoliza());

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

    // ==================== Create Póliza ====================

    public PolizaUpdatedDTO crearPoliza(CreatePolizaDTO dto){
        PolizaUpdated polizaUpdated = polizaUpdatedMapper.toEntity(dto);

        polizaUpdated.setEstadoSeguro("Activa");

        //Extraer información de la sesión autenticada
        //Authentication auth= SecurityContextHolder.getContext().getAuthentication();

        //auth.getName() extrae el nombre de usuario autenticado en la sesión
        //usuario.setCreadoPor(auth.getName());
        polizaUpdated.setCreadorPoliza("Creator Dummy");

        polizaUpdated.setFechaCreacionPoliza(LocalDate.now());

        PolizaUpdated guardado = polizaUpdatedRepository.save(polizaUpdated);

        return polizaUpdatedMapper.toDTO(guardado);
    }

    // ============================================================

    // ==================== Update Póliza ====================

    public PolizaUpdatedDTO modificarPoliza(Long id, UpdatePolizaDTO dto) {
        PolizaUpdated polizaUpdated = polizaUpdatedRepository.findById(id).orElseThrow(() -> new RuntimeException("Póliza no encontrada."));

        //Las condicionales ayudan a que no sea necesario editar todos los campos, sino solo los deseados (en conjunción, el DTO de update no tiene Beans de validaciones).

        if (dto.getNombreAsegurado() != null) {
            polizaUpdated.setNombreAsegurado(dto.getNombreAsegurado());
        }

        if (dto.getNombreBeneficiario() != null) {
            polizaUpdated.setNombreBeneficiario(dto.getNombreBeneficiario());
        }

        if (dto.getTipoSeguro() != null) {
            polizaUpdated.setTipoSeguro(dto.getTipoSeguro());
        }

        if (dto.getEstadoSeguro() != null) {
            polizaUpdated.setEstadoSeguro(dto.getEstadoSeguro());
        }

        if (dto.getPrimaMensual() != null) {
            polizaUpdated.setPrimaMensual(dto.getPrimaMensual());
        }

        if (dto.getCobertura() != null) {
            polizaUpdated.setCobertura(dto.getCobertura());
        }

        if (dto.getFechaInicio() != null) {
            polizaUpdated.setFechaInicio(dto.getFechaInicio());
        }

        if (dto.getFechaVencimiento() != null) {
            polizaUpdated.setFechaVencimiento(dto.getFechaVencimiento());
        }

        if (dto.getDescripcionPoliza() != null) {
            polizaUpdated.setDescripcionPoliza(dto.getDescripcionPoliza());
        }

        //Campos de auditoría

        polizaUpdated.setModificadorRegistroPoliza("Updater Dummy");
        polizaUpdated.setFechaModificacionPoliza(LocalDate.now());

        /*
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        usuario.setModificadoPor(auth.getName());
        usuario.setFechaModificacion(LocalDate.now());
         */

        PolizaUpdated actualizado = polizaUpdatedRepository.save(polizaUpdated);

        return polizaUpdatedMapper.toDTO(actualizado);
    }

    // ============================================================

    // ==================== Delete Póliza ====================

    //CHANTAL
    public void eliminarPoliza(Long id) {
        if (!polizaUpdatedRepository.existsById(id)) {
            throw new RuntimeException("La póliza con ID " + id + " no existe.");
        }

        polizaUpdatedRepository.deleteById(id);
    }

    // ============================================================

}

