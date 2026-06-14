package com.farmacia.mscliente.service.impl;

import com.farmacia.mscliente.dto.ClienteRequestDTO;
import com.farmacia.mscliente.dto.ClienteResponseDTO;
import com.farmacia.mscliente.exceptions.DuplicateEmailException;
import com.farmacia.mscliente.exceptions.ResourceNotFoundException;
import com.farmacia.mscliente.mapper.ClienteMapper;
import com.farmacia.mscliente.model.Cliente;
import com.farmacia.mscliente.repository.ClienteRepository;
import com.farmacia.mscliente.service.ClienteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ClienteServiceImpl
        implements ClienteService {

    private final ClienteRepository repository;

    private final ClienteMapper mapper;

    @Override
    public ClienteResponseDTO crear(
            ClienteRequestDTO dto
    ) {

        log.info(
                "Intentando crear cliente con email: {}",
                dto.getEmail()
        );

        if (repository.existsByEmail(dto.getEmail())) {

            log.warn(
                    "Email duplicado detectado: {}",
                    dto.getEmail()
            );

            throw new DuplicateEmailException(
                    "El email ya está registrado"
            );
        }

        Cliente cliente = mapper.toEntity(dto);

        Cliente guardado =
                repository.save(cliente);

        log.info(
                "Cliente creado correctamente con id: {}",
                guardado.getId()
        );

        return mapper.toDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponseDTO obtenerPorId(
            Long id
    ) {

        log.info(
                "Buscando cliente con id: {}",
                id
        );

        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> {

                    log.error(
                            "Cliente no encontrado con id: {}",
                            id
                    );

                    return new ResourceNotFoundException(
                            "Cliente no encontrado"
                    );
                });

        return mapper.toDTO(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> listar() {

        log.info(
                "Listando todos los clientes"
        );

        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public ClienteResponseDTO actualizar(
            Long id,
            ClienteRequestDTO dto
    ) {

        log.info(
                "Actualizando cliente con id: {}",
                id
        );

        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> {

                    log.error(
                            "Cliente no encontrado para actualizar: {}",
                            id
                    );

                    return new ResourceNotFoundException(
                            "Cliente no encontrado"
                    );
                });

        if (!cliente.getEmail().equals(dto.getEmail())
                && repository.existsByEmail(dto.getEmail())) {

            log.warn(
                    "Intento de actualizar con email duplicado: {}",
                    dto.getEmail()
            );

            throw new DuplicateEmailException(
                    "El email ya está en uso"
            );
        }

        cliente.setNombre(dto.getNombre());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());

        Cliente actualizado =
                repository.save(cliente);

        log.info(
                "Cliente actualizado correctamente con id: {}",
                id
        );

        return mapper.toDTO(actualizado);
    }

    @Override
    public void eliminar(Long id) {

        log.info(
                "Eliminando cliente con id: {}",
                id
        );

        if (!repository.existsById(id)) {

            log.error(
                    "Cliente no encontrado para eliminar: {}",
                    id
            );

            throw new ResourceNotFoundException(
                    "Cliente no encontrado"
            );
        }

        repository.deleteById(id);

        log.info(
                "Cliente eliminado correctamente con id: {}",
                id
        );
    }
}