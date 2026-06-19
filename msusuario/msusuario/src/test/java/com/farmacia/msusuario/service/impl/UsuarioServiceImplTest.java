package com.farmacia.msusuario.service.impl;
import com.farmacia.msusuario.dto.UsuarioRequestDTO;
import com.farmacia.msusuario.dto.UsuarioResponseDTO;
import com.farmacia.msusuario.exception.DuplicateEmailException;
import com.farmacia.msusuario.exception.ResourceNotFoundException;
import com.farmacia.msusuario.mapper.UsuarioMapper;
import com.farmacia.msusuario.model.Rol;
import com.farmacia.msusuario.model.Usuario;
import com.farmacia.msusuario.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UsuarioMapper mapper;

    @InjectMocks
    private UsuarioServiceImpl service;

    private Usuario usuario;
    private UsuarioRequestDTO requestDTO;
    private UsuarioResponseDTO responseDTO;

    @BeforeEach
    void setUp() {

        usuario = Usuario.builder()
                .id(1L)
                .nombre("Juan")
                .email("juan@correo.cl")
                .password("123456")
                .rol(Rol.ADMIN)
                .build();

        requestDTO = new UsuarioRequestDTO(
                "Juan",
                "juan@correo.cl",
                "123456",
                Rol.ADMIN
        );

        responseDTO = UsuarioResponseDTO.builder()
                .id(1L)
                .nombre("Juan")
                .email("juan@correo.cl")
                .rol(Rol.ADMIN)
                .build();
    }

    @Test
    @DisplayName("Debe crear usuario correctamente")
    void crearUsuarioOk() {

        when(repository.existsByEmail(requestDTO.getEmail()))
                .thenReturn(false);

        when(mapper.toEntity(requestDTO))
                .thenReturn(usuario);

        when(passwordEncoder.encode("123456"))
                .thenReturn("password-encriptada");

        when(repository.save(any(Usuario.class)))
                .thenReturn(usuario);

        when(mapper.toDTO(usuario))
                .thenReturn(responseDTO);

        UsuarioResponseDTO resultado =
                service.crearUsuario(requestDTO);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());

        verify(repository, times(1))
                .save(any(Usuario.class));
    }

    @Test
    @DisplayName("Debe lanzar excepción si email existe")
    void crearUsuarioEmailDuplicado() {

        when(repository.existsByEmail(requestDTO.getEmail()))
                .thenReturn(true);

        assertThrows(
                DuplicateEmailException.class,
                () -> service.crearUsuario(requestDTO)
        );

        verify(repository, never())
                .save(any());
    }

    @Test
    @DisplayName("Debe obtener usuario por ID")
    void obtenerUsuarioPorIdOk() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(usuario));

        when(mapper.toDTO(usuario))
                .thenReturn(responseDTO);

        UsuarioResponseDTO resultado =
                service.obtenerUsuarioPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    @DisplayName("Debe lanzar excepción si usuario no existe")
    void obtenerUsuarioNoExiste() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.obtenerUsuarioPorId(1L)
        );
    }

    @Test
    @DisplayName("Debe listar usuarios")
    void listarUsuariosOk() {

        when(repository.findAll())
                .thenReturn(List.of(usuario));

        when(mapper.toDTO(usuario))
                .thenReturn(responseDTO);

        List<UsuarioResponseDTO> lista =
                service.listarUsuarios();

        assertEquals(1, lista.size());
    }

    @Test
    @DisplayName("Debe eliminar usuario")
    void eliminarUsuarioOk() {

        when(repository.existsById(1L))
                .thenReturn(true);

        service.eliminarUsuario(1L);

        verify(repository, times(1))
                .deleteById(1L);
    }

    @Test
    @DisplayName("Debe lanzar excepción al eliminar inexistente")
    void eliminarUsuarioNoExiste() {

        when(repository.existsById(1L))
                .thenReturn(false);

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.eliminarUsuario(1L)
        );
    }
}



