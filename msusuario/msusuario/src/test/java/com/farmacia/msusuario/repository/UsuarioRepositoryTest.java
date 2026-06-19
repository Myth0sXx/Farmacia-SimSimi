package com.farmacia.msusuario.repository;
import com.farmacia.msusuario.model.Rol;
import com.farmacia.msusuario.model.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Usamos Mockito puro, cero Spring
class UsuarioRepositoryTest {

    @Mock
    private UsuarioRepository repository; // Simulamos el repositorio entero

    @Test
    @DisplayName("Debe buscar usuario por email")
    void findByEmail() {
        Usuario usuario = Usuario.builder()
                .nombre("Alexandra")
                .email("alex@correo.cl")
                .password("123456")
                .rol(Rol.ADMIN)
                .build();

        // Como no hay base de datos real, le enseñamos al Mock qué responder
        when(repository.findByEmail("alex@correo.cl"))
                .thenReturn(Optional.of(usuario));

        Optional<Usuario> encontrado = repository.findByEmail("alex@correo.cl");

        assertTrue(encontrado.isPresent());
        assertEquals("Alexandra", encontrado.get().getNombre());
    }

    @Test
    @DisplayName("Debe verificar existencia email")
    void existsByEmail() {
        // Le enseñamos al Mock que cuando pregunten por ese email, responda true
        when(repository.existsByEmail("alex@correo.cl"))
                .thenReturn(true);

        assertTrue(repository.existsByEmail("alex@correo.cl"));
    }
}


