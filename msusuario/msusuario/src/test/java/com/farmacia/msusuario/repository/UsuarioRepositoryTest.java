package com.farmacia.msusuario.repository;
import com.farmacia.msusuario.model.Rol;
import com.farmacia.msusuario.model.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repository;

    @Test
    @DisplayName("Debe buscar usuario por email")
    void findByEmail() {

        Usuario usuario = Usuario.builder()
                .nombre("Juan")
                .email("juan@correo.cl")
                .password("123456")
                .rol(Rol.ADMIN)
                .build();

        repository.save(usuario);

        Optional<Usuario> encontrado =
                repository.findByEmail("juan@correo.cl");

        assertTrue(encontrado.isPresent());
    }

    @Test
    @DisplayName("Debe verificar existencia email")
    void existsByEmail() {

        Usuario usuario = Usuario.builder()
                .nombre("Juan")
                .email("juan@correo.cl")
                .password("123456")
                .rol(Rol.ADMIN)
                .build();

        repository.save(usuario);

        assertTrue(
                repository.existsByEmail("juan@correo.cl")
        );
    }
}


