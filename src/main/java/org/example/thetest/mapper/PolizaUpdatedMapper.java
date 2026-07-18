package org.example.thetest.mapper;

import org.example.thetest.dto.CreatePolizaDTO;
import org.example.thetest.dto.PolizaUpdatedDTO;
import org.example.thetest.entity.PolizaUpdated;
import org.mapstruct.Mapper;

//import org.example.thetest.dto.CreatePoliza;

@Mapper(componentModel = "spring")
public interface PolizaUpdatedMapper {
    PolizaUpdatedDTO toDTO(PolizaUpdated polizaUpdated);

    PolizaUpdated toEntity(CreatePolizaDTO dto);
}