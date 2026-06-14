package com.farmacia.mscliente.mapper;

import com.farmacia.mscliente.dto.ClienteRequestDTO;
import com.farmacia.mscliente.dto.ClienteResponseDTO;
import com.farmacia.mscliente.model.Cliente;

import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public Cliente toEntity(
            ClienteRequestDTO dto
    ) {

        Cliente cliente = new Cliente();

        cliente.setNombre(dto.getNombre());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());

        return cliente;
    }

    public ClienteResponseDTO toDTO(
            Cliente cliente
    ) {

        return ClienteResponseDTO.builder()
                .id(cliente.getId())
                .nombre(cliente.getNombre())
                .email(cliente.getEmail())
                .telefono(cliente.getTelefono())
                .build();
    }
}