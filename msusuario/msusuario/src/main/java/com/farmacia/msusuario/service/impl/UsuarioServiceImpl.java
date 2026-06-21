package com.farmacia.msusuario.service.impl;
import com.farmacia.msusuario.dto.request.UsuarioRequestDTO;
import com.farmacia.msusuario.dto.response.UsuarioResponseDTO;
import com.farmacia.msusuario.exception.DuplicateEmailException;
import com.farmacia.msusuario.exception.ResourceNotFoundException;
import com.farmacia.msusuario.mapper.UsuarioMapper;
import com.farmacia.msusuario.model.Usuario;
import com.farmacia.msusuario.repository.UsuarioRepository;
import com.farmacia.msusuario.service.interfaces.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper mapper;

    @Override
    public UsuarioResponseDTO crearUsuario(UsuarioRequestDTO dto) {

        log.info("Creando usuario {}", dto.getEmail());

        if (repository.existsByEmail(dto.getEmail())) {
            throw new DuplicateEmailException("El email ya está registrado");
        }

        Usuario usuario = mapper.toEntity(dto);

        usuario.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );

        Usuario guardado = repository.save(usuario);

        log.info("Usuario creado con ID {}", guardado.getId());

        return mapper.toDTO(guardado);
    }

    @Override
    public UsuarioResponseDTO obtenerUsuarioPorId(Long id) {

        log.info("Buscando usuario {}", id);

        Usuario usuario = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"
                        ));

        return mapper.toDTO(usuario);
    }

    @Override
    public List<UsuarioResponseDTO> listarUsuarios() {

        log.info("Listando usuarios");

        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public UsuarioResponseDTO actualizarUsuario(
            Long id,
            UsuarioRequestDTO dto
    ) {

        log.info("Actualizando usuario {}", id);

        Usuario usuario = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"
                        ));

        if (!usuario.getEmail().equals(dto.getEmail())
                && repository.existsByEmail(dto.getEmail())) {

            throw new DuplicateEmailException(
                    "Email ya en uso"
            );
        }

        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setRol(dto.getRol());

        if (dto.getPassword() != null
                && !dto.getPassword().isBlank()) {

            usuario.setPassword(
                    passwordEncoder.encode(
                            dto.getPassword()
                    )
            );
        }

        Usuario actualizado = repository.save(usuario);

        log.info("Usuario {} actualizado correctamente", id);

        return mapper.toDTO(actualizado);
    }

    @Override
    public void eliminarUsuario(Long id) {

        log.info("Eliminando usuario {}", id);

        Usuario usuario = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"
                        ));

        repository.delete(usuario);

        log.info("Usuario {} eliminado correctamente", id);

    }
}
