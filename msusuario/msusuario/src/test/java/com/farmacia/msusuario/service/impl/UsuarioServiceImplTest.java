package com.farmacia.msusuario.service.impl;
import com.farmacia.msusuario.dto.request.UsuarioRequestDTO;
import com.farmacia.msusuario.dto.response.UsuarioResponseDTO;
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
        // GIVEN (Preparación del escenario y simulación de dependencias)
        // Cambia esto en UsuarioServiceImplTest.java -> crearUsuarioOk()
        when(repository.existsByEmail(requestDTO.getEmail())).thenReturn(false);
        when(mapper.toEntity(requestDTO)).thenReturn(usuario);
        when(passwordEncoder.encode(anyString())).thenReturn("password-encriptada");
        when(repository.save(any(Usuario.class))).thenReturn(usuario);
        when(mapper.toDTO(usuario)).thenReturn(responseDTO);


        // WHEN (Ejecución del método de negocio bajo prueba)
        UsuarioResponseDTO resultado = service.crearUsuario(requestDTO);


        // THEN (Verificaciones de comportamiento y aserciones)
        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
        verify(repository, times(1)).save(any(Usuario.class));
    }


    @Test
    @DisplayName("Debe lanzar excepción si email existe")
    void crearUsuarioEmailDuplicado() {
        // GIVEN
        when(repository.existsByEmail(requestDTO.getEmail())).thenReturn(true);


        // WHEN & THEN
        assertThrows(
                DuplicateEmailException.class,
                () -> service.crearUsuario(requestDTO)
        );
        verify(repository, never()).save(any());
    }


    @Test
    @DisplayName("Debe obtener usuario por ID")
    void obtenerUsuarioPorIdOk() {
        // GIVEN
        when(repository.findById(1L)).thenReturn(Optional.of(usuario));
        when(mapper.toDTO(usuario)).thenReturn(responseDTO);


        // WHEN
        UsuarioResponseDTO resultado = service.obtenerUsuarioPorId(1L);


        // THEN
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }


    @Test
    @DisplayName("Debe lanzar excepción si usuario no existe")
    void obtenerUsuarioNoExiste() {
        // GIVEN
        when(repository.findById(1L)).thenReturn(Optional.empty());


        // WHEN & THEN
        assertThrows(
                ResourceNotFoundException.class,
                () -> service.obtenerUsuarioPorId(1L)
        );
    }


    @Test
    @DisplayName("Debe listar usuarios")
    void listarUsuariosOk() {
        // GIVEN
        when(repository.findAll()).thenReturn(List.of(usuario));
        when(mapper.toDTO(usuario)).thenReturn(responseDTO);


        // WHEN
        List<UsuarioResponseDTO> lista = service.listarUsuarios();


        // THEN
        assertEquals(1, lista.size());
        verify(repository, times(1)).findAll();
    }


    @Test
    @DisplayName("Debe eliminar usuario")
    void eliminarUsuarioOk() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(usuario));

        service.eliminarUsuario(1L);

        verify(repository, times(1))
                .delete(usuario);
    }

    @Test
    @DisplayName("Debe lanzar excepción al eliminar inexistente")
    void eliminarUsuarioNoExiste() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.eliminarUsuario(1L)
        );

        verify(repository, never())
                .delete(any(Usuario.class));
    }

    @Test
    @DisplayName("Debe actualizar usuario correctamente")
    void actualizarUsuarioOk() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(usuario));

        when(repository.save(any(Usuario.class)))
                .thenReturn(usuario);

        when(mapper.toDTO(usuario))
                .thenReturn(responseDTO);

        when(passwordEncoder.encode(anyString()))
                .thenReturn("password-encriptada");

        UsuarioResponseDTO resultado =
                service.actualizarUsuario(1L, requestDTO);

        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());

        verify(repository, times(1))
                .save(any(Usuario.class));
    }

    @Test
    @DisplayName("Debe lanzar excepción al actualizar usuario inexistente")
    void actualizarUsuarioNoExiste() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.actualizarUsuario(1L, requestDTO)
        );
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el email ya existe al actualizar")
    void actualizarUsuarioEmailDuplicado() {

        Usuario usuarioExistente = Usuario.builder()
                .id(1L)
                .nombre("Juan")
                .email("correoantiguo@correo.cl")
                .password("123456")
                .rol(Rol.ADMIN)
                .build();

        when(repository.findById(1L))
                .thenReturn(Optional.of(usuarioExistente));

        when(repository.existsByEmail(requestDTO.getEmail()))
                .thenReturn(true);

        assertThrows(
                DuplicateEmailException.class,
                () -> service.actualizarUsuario(1L, requestDTO)
        );

        verify(repository, never())
                .save(any(Usuario.class));
    }
}



