package com.hotelmania.service.mapper;

import com.hotelmania.model.Cliente;
import com.hotelmania.service.dto.ClienteDto;
import com.hotelmania.service.form.ClienteForm;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    Cliente toModel(ClienteDto clienteDto);

    ClienteDto toDto(Cliente cliente);
    Cliente toEntity(ClienteForm clienteForm);

    List<ClienteDto> toDtos(List<Cliente> clienteList);

    Cliente copiar(Cliente cliente);

    void atualizarCliente(ClienteForm form, @MappingTarget Cliente cliente);


}
