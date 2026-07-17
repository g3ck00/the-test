package org.example.thetest.mapper;

import org.example.thetest.entity.Cliente;
//import org.example.thetest.dto.CrearClienteDTO;
import org.example.thetest.dto.ClienteDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteDTO toDTO(Cliente cliente);

    //Cliente toEntity(CrearClienteDTO dto);
}
