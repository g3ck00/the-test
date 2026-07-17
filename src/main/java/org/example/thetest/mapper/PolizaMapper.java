package org.example.thetest.mapper;

import org.example.projecttwo.entity.Rol;
import org.example.projecttwo.dto.CrearRolDTO;
import org.example.projecttwo.dto.RolDTO;
import org.example.thetest.dto.PolizaDTO;
import org.example.thetest.entity.Poliza;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PolizaMapper {
    PolizaDTO toDTO(Poliza poliza);

    //Poliza toEntity(CrearPolizaDTO dto);
}