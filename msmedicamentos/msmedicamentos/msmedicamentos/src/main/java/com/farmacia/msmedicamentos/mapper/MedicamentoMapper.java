package com.farmacia.msmedicamentos.mapper;

import com.farmacia.msmedicamentos.dto.MedicamentoRequestDTO;
import com.farmacia.msmedicamentos.dto.MedicamentoResponseDTO;
import com.farmacia.msmedicamentos.model.Medicamento;

import org.springframework.stereotype.Component;

@Component
public class MedicamentoMapper {

    public Medicamento toEntity(
            MedicamentoRequestDTO dto
    ) {

        Medicamento medicamento =
                new Medicamento();

        medicamento.setNombre(dto.getNombre());

        medicamento.setDescripcion(
                dto.getDescripcion()
        );

        medicamento.setPrecio(
                dto.getPrecio()
        );

        medicamento.setRequiereReceta(
                dto.getRequiereReceta()
        );

        return medicamento;
    }

    public MedicamentoResponseDTO toDTO(
            Medicamento medicamento
    ) {

        return MedicamentoResponseDTO.builder()
                .id(medicamento.getId())
                .nombre(medicamento.getNombre())
                .descripcion(
                        medicamento.getDescripcion()
                )
                .precio(
                        medicamento.getPrecio()
                )
                .requiereReceta(
                        medicamento.getRequiereReceta()
                )
                .build();
    }
}