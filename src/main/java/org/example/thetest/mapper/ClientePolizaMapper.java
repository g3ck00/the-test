package org.example.thetest.mapper;

import org.example.thetest.dto.ReadAllClientePolizaDTO;
import org.example.thetest.entity.ClientePoliza;
//import org.example.thetest.dto.CrearClientePolizaDTO;
import org.example.thetest.dto.ReadAllClientePolizaDTO;
import org.example.thetest.dto.ReadAllClientePolizaDTO;
import org.example.thetest.entity.ClientePoliza;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientePolizaMapper {

    @Mapping(source="cliente.idCliente",target="idCliente")
    @Mapping(source="poliza.idPoliza",target="idPoliza")
    ReadAllClientePolizaDTO toDTO(ClientePoliza clientePoliza);

    //ClientePoliza toEntity(CrearClientePolizaDTO dto);
}
