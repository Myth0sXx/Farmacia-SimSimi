package com.farmacia.msusuario.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.farmacia.msusuario.dto.request.UsuarioRequestDTO;
import com.farmacia.msusuario.dto.response.UsuarioResponseDTO;
import com.farmacia.msusuario.model.Rol;
import com.farmacia.msusuario.service.interfaces.UsuarioService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private UsuarioService service;

    @InjectMocks
    private UsuarioController controller;

    private UsuarioRequestDTO request;
    private UsuarioResponseDTO response;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();

        request = new UsuarioRequestDTO(
                "Juan",
                "juan@correo.cl",
                "123456",
                Rol.ADMIN
        );

        response = UsuarioResponseDTO.builder()
                .id(1L)
                .nombre("Juan")
                .email("juan@correo.cl")
                .rol(Rol.ADMIN)
                .build();
    }
    @Test
    @DisplayName("Debe crear usuario")
    void crearUsuario() throws Exception {

        when(service.crearUsuario(any(UsuarioRequestDTO.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    @DisplayName("Debe obtener usuario por ID")
    void obtenerUsuarioPorId() throws Exception {

        when(service.obtenerUsuarioPorId(1L))
                .thenReturn(response);

        mockMvc.perform(get("/api/v1/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email")
                        .value("juan@correo.cl"));
    }

    @Test
    @DisplayName("Debe listar usuarios")
    void listarUsuarios() throws Exception {

        when(service.listarUsuarios())
                .thenReturn(List.of(response));

        mockMvc.perform(get("/api/v1/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre")
                        .value("Juan"));
    }

    @Test
    @DisplayName("Debe actualizar usuario")
    void actualizarUsuario() throws Exception {

        when(service.actualizarUsuario(
                eq(1L),
                any(UsuarioRequestDTO.class)
        )).thenReturn(response);

        mockMvc.perform(put("/api/v1/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre")
                        .value("Juan"));
    }

    @Test
    @DisplayName("Debe eliminar usuario")
    void eliminarUsuario() throws Exception {

        mockMvc.perform(delete("/api/v1/usuarios/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Debe retornar 400 cuando email es inválido")
    void crearUsuarioEmailInvalido() throws Exception {

        UsuarioRequestDTO requestInvalido =
                new UsuarioRequestDTO(
                        "Juan",
                        "correo-invalido",
                        "123456",
                        Rol.ADMIN
                );

        mockMvc.perform(
                        post("/api/v1/usuarios")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestInvalido))
                )
                .andExpect(status().isBadRequest());
    }
}




