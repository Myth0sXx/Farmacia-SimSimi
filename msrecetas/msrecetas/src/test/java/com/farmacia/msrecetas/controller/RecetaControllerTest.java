package com.farmacia.msrecetas.controller;
import com.farmacia.msrecetas.dto.validar.ValidarRecetaDTO;
import com.farmacia.msrecetas.service.intefaces.RecetaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.farmacia.msrecetas.dto.request.RecetaRequestDTO;
import com.farmacia.msrecetas.dto.response.RecetaResponseDTO;
import com.farmacia.msrecetas.model.EstadoReceta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import static org.mockito.Mockito.verify;
import java.util.List;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecetaController.class)

public class RecetaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RecetaService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /api/v1/recetas - Debería retornar 201 Created al enviar DTO válido")
    void crearRecetaControllerExito() throws Exception {
        // Arrange
        RecetaRequestDTO requestDTO = new RecetaRequestDTO(50L, "Dra. Polo");
        RecetaResponseDTO responseDTO = RecetaResponseDTO.builder()
                .id(12L)
                .clienteId(50L)
                .doctorNombre("Dra. Polo")
                .fecha(LocalDate.now())
                .estado(EstadoReceta.ACTIVA)
                .build();

        when(service.crearReceta(any(RecetaRequestDTO.class))).thenReturn(responseDTO);

        // Act & Assert
        mockMvc.perform(post("/api/v1/recetas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(12L))
                .andExpect(jsonPath("$.doctorNombre").value("Dra. Polo"))
                .andExpect(jsonPath("$.estado").value("ACTIVA"));
    }

    @Test
    @DisplayName("POST /api/v1/recetas - Debería retornar 400 Bad Request cuando faltan campos obligatorios")
    void crearRecetaControllerErrorValidacion() throws Exception {
        // Arrange - Enviamos doctor vacío y cliente nulo para forzar los errores de @Valid
        RecetaRequestDTO requestInvalido = new RecetaRequestDTO(null, "");

        // Act & Assert
        mockMvc.perform(post("/api/v1/recetas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestInvalido)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.clienteId").value("El cliente es obligatorio"))
                .andExpect(jsonPath("$.doctorNombre").value("El nombre del doctor es obligatorio"));
    }
    @Test
    @DisplayName("GET /api/v1/recetas")
    void listarRecetasController() throws Exception {

        RecetaResponseDTO dto =
                RecetaResponseDTO.builder()
                        .id(1L)
                        .clienteId(50L)
                        .doctorNombre("Dra. Polo")
                        .estado(EstadoReceta.ACTIVA)
                        .fecha(LocalDate.now())
                        .build();

        when(service.listarRecetas())
                .thenReturn(List.of(dto));

        mockMvc.perform(get("/api/v1/recetas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].doctorNombre").value("Dra. Polo"))
                .andExpect(jsonPath("$[0].estado").value("ACTIVA"));
    }
    @Test
    @DisplayName("POST validar receta")
    void validarRecetaController() throws Exception {

        ValidarRecetaDTO dto =
                new ValidarRecetaDTO(50L);

        when(service.validarReceta(any()))
                .thenReturn(true);

        mockMvc.perform(post("/api/v1/recetas/validar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
    @Test
    @DisplayName("PUT usar receta")
    void usarRecetaController() throws Exception {

        doNothing().when(service)
                .marcarComoUsada(1L);

        mockMvc.perform(
                        put("/api/v1/recetas/1/usar")
                )
                .andExpect(status().isNoContent());

        verify(service).marcarComoUsada(1L);
    }

}
