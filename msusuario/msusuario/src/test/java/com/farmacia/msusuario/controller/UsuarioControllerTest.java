package com.farmacia.msusuario.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.farmacia.msusuario.dto.UsuarioRequestDTO;
import com.farmacia.msusuario.dto.UsuarioResponseDTO;
import com.farmacia.msusuario.model.Rol;
import com.farmacia.msusuario.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class) // 1. Usamos la extensión pura de Mockito
class UsuarioControllerTest {

    private MockMvc mockMvc;

    // 2. Instanciamos el mapeador de JSON manualmente
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private UsuarioService service; // 3. Mock clásico de Mockito en vez de @MockitoBean

    @InjectMocks
    private UsuarioController usuarioController; // 4. Mockito inyecta el 'service' aquí dentro

    @BeforeEach
    void setUp() {
        // 5. Construimos el entorno web simulado manualmente antes de cada test
        this.mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
    }

    @Test
    @DisplayName("POST crear usuario")
    void crearUsuario() throws Exception {

        UsuarioRequestDTO request =
                new UsuarioRequestDTO(
                        "Juan",
                        "Juan@correo.cl",
                        "123456",
                        Rol.ADMIN
                );

        UsuarioResponseDTO response =
                UsuarioResponseDTO.builder()
                        .id(1L)
                        .nombre("Juan")
                        .email("juan@correo.cl")
                        .rol(Rol.ADMIN)
                        .build();

        when(service.crearUsuario(request))
                .thenReturn(response);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    @DisplayName("GET usuario por id")
    void obtenerUsuario() throws Exception {

        UsuarioResponseDTO response =
                UsuarioResponseDTO.builder()
                        .id(1L)
                        .nombre("Juan")
                        .email("alex@correo.cl")
                        .rol(Rol.ADMIN)
                        .build();

        when(service.obtenerUsuarioPorId(1L))
                .thenReturn(response);

        mockMvc.perform(get("/api/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @DisplayName("GET listar usuarios")
    void listarUsuarios() throws Exception {

        UsuarioResponseDTO usuario =
                UsuarioResponseDTO.builder()
                        .id(1L)
                        .nombre("Juan")
                        .email("juan@correo.cl")
                        .rol(Rol.ADMIN)
                        .build();

        when(service.listarUsuarios())
                .thenReturn(List.of(usuario));

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre")
                        .value("Juan"));
    }
}





