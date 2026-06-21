package com.farmacia.msreportes.controller;
import com.farmacia.msreportes.dto.request.ReporteRequestDTO;
import com.farmacia.msreportes.dto.response.ReporteResponseDTO;
import com.farmacia.msreportes.exception.ResourceNotFoundException;
import com.farmacia.msreportes.service.interfaces.ReporteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReporteController.class)
class ReporteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ReporteService service;

    private ReporteResponseDTO dtoMock;

    @BeforeEach
    void setUp() {
        dtoMock = new ReporteResponseDTO(
                1L,
                "REPORTE_VENTAS",
                LocalDateTime.now(),
                "[]"
        );
    }

    @Test
    @DisplayName("POST /api/v1/reportes -> 200 OK")
    void generarReporte() throws Exception {

        ReporteRequestDTO request = new ReporteRequestDTO("REPORTE_VENTAS");

        when(service.generarReporte("REPORTE_VENTAS"))
                .thenReturn(dtoMock);

        mockMvc.perform(post("/api/v1/reportes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.tipo").value("REPORTE_VENTAS"));
    }

    @Test
    @DisplayName("GET /api/v1/reportes -> lista")
    void listarReportes() throws Exception {

        when(service.listarReportes())
                .thenReturn(List.of(dtoMock));

        mockMvc.perform(get("/api/v1/reportes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    @DisplayName("GET /api/v1/reportes/{id} -> OK")
    void obtenerPorId() throws Exception {

        when(service.obtenerPorId(1L))
                .thenReturn(dtoMock);

        mockMvc.perform(get("/api/v1/reportes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    @DisplayName("GET /api/v1/reportes/{id} -> 404")
    void obtenerPorIdNotFound() throws Exception {

        when(service.obtenerPorId(99L))
                .thenThrow(new ResourceNotFoundException("Reporte no encontrado"));

        mockMvc.perform(get("/api/v1/reportes/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensaje").value("Reporte no encontrado"));
    }
}
