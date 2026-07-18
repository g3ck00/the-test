package org.example.thetest.mapper;

import org.example.thetest.entity.Poliza;
//import org.example.thetest.dto.CreatePoliza;
import org.example.thetest.dto.PolizaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PolizaMapper {
    PolizaDTO toDTO(Poliza poliza);

    //Poliza toEntity(createPoliza dto);
}