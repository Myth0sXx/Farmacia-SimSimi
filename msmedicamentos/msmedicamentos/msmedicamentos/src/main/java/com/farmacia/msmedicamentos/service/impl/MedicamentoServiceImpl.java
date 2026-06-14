package com.farmacia.msmedicamentos.service.impl;

import com.farmacia.msmedicamentos.dto.MedicamentoRequestDTO;
import com.farmacia.msmedicamentos.dto.MedicamentoResponseDTO;
import com.farmacia.msmedicamentos.exception.ResourceNotFoundException;
import com.farmacia.msmedicamentos.mapper.MedicamentoMapper;
import com.farmacia.msmedicamentos.model.Medicamento;
import com.farmacia.msmedicamentos.repository.MedicamentoRepository;
import com.farmacia.msmedicamentos.service.MedicamentoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MedicamentoServiceImpl
        implements MedicamentoService {

    private final MedicamentoRepository repository;

    private final MedicamentoMapper mapper;

    @Override
    public MedicamentoResponseDTO crear(
            MedicamentoRequestDTO dto
    ) {

        log.info(
                "Creando medicamento: {}",
                dto.getNombre()
        );

        Medicamento medicamento =
                mapper.toEntity(dto);

        Medicamento guardado =
                repository.save(medicamento);

        log.info(
                "Medicamento creado correctamente con id: {}",
                guardado.getId()
        );

        return mapper.toDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicamentoResponseDTO> listar() {

        log.info(
                "Listando medicamentos"
        );

        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MedicamentoResponseDTO obtenerPorId(
            Long id
    ) {

        log.info(
                "Buscando medicamento con id: {}",
                id
        );

        Medicamento medicamento =
                repository.findById(id)
                        .orElseThrow(() -> {

                            log.error(
                                    "Medicamento no encontrado con id: {}",
                                    id
                            );

                            return new ResourceNotFoundException(
                                    "Medicamento no encontrado"
                            );
                        });

        return mapper.toDTO(medicamento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicamentoResponseDTO>
    buscarPorNombre(
            String nombre
    ) {

        log.info(
                "Buscando medicamentos por nombre: {}",
                nombre
        );

        return repository
                .findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public MedicamentoResponseDTO actualizar(
            Long id,
            MedicamentoRequestDTO dto
    ) {

        log.info(
                "Actualizando medicamento con id: {}",
                id
        );

        Medicamento medicamento =
                repository.findById(id)
                        .orElseThrow(() -> {

                            log.error(
                                    "Medicamento no encontrado con id: {}",
                                    id
                            );

                            return new ResourceNotFoundException(
                                    "Medicamento no encontrado"
                            );
                        });

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

        Medicamento actualizado =
                repository.save(medicamento);

        log.info(
                "Medicamento actualizado correctamente con id: {}",
                id
        );

        return mapper.toDTO(actualizado);
    }

    @Override
    public void eliminar(Long id) {

        log.info(
                "Intentando eliminar medicamento con id: {}",
                id
        );

        if (!repository.existsById(id)) {

            log.error(
                    "No se pudo eliminar. Medicamento no encontrado con id: {}",
                    id
            );

            throw new ResourceNotFoundException(
                    "Medicamento no encontrado"
            );
        }

        repository.deleteById(id);

        log.info(
                "Medicamento eliminado correctamente con id: {}",
                id
        );
    }
}