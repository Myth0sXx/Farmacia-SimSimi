package com.farmacia.msdetalleventa.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.farmacia.msdetalleventa.dto.request.DetalleVentaRequestDTO;
import com.farmacia.msdetalleventa.dto.response.DetalleVentaResponseDTO;
import com.farmacia.msdetalleventa.service.interfaces.DetalleVentaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(DetalleVentaController.class)
class DetalleVentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DetalleVentaService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearDetalle_DeberiaRetornarCreated()
            throws Exception {

        DetalleVentaRequestDTO request =
                new DetalleVentaRequestDTO();

        request.setVentaId(1L);
        request.setMedicamentoId(2L);
        request.setCantidad(5);
        request.setPrecioUnitario(1000.0);

        DetalleVentaResponseDTO response =
                DetalleVentaResponseDTO.builder()
                        .id(1L)
                        .ventaId(1L)
                        .medicamentoId(2L)
                        .cantidad(5)
                        .precioUnitario(1000.0)
                        .build();

        when(service.crearDetalle(any()))
                .thenReturn(response);

        mockMvc.perform(post("/api/detalle-venta")
                        .contentType(
                                MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper
                                        .writeValueAsString(
                                                request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id")
                        .value(1));
    }

    @Test
    void crearDetalle_DeberiaRetornarBadRequest()
            throws Exception {

        DetalleVentaRequestDTO request =
                new DetalleVentaRequestDTO();

        request.setVentaId(null);

        mockMvc.perform(post("/api/detalle-venta")
                        .contentType(
                                MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper
                                        .writeValueAsString(
                                                request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void crearMultiples_DeberiaRetornarOk()
            throws Exception {

        DetalleVentaRequestDTO request =
                new DetalleVentaRequestDTO();

        request.setVentaId(1L);
        request.setMedicamentoId(2L);
        request.setCantidad(5);
        request.setPrecioUnitario(1000.0);

        DetalleVentaResponseDTO response =
                DetalleVentaResponseDTO.builder()
                        .id(1L)
                        .ventaId(1L)
                        .medicamentoId(2L)
                        .cantidad(5)
                        .precioUnitario(1000.0)
                        .build();

        when(service.crearMultiples(any()))
                .thenReturn(List.of(response));

        mockMvc.perform(post(
                        "/api/detalle-venta/lote")
                        .contentType(
                                MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper
                                        .writeValueAsString(
                                                List.of(request))))
                .andExpect(status().isOk());
    }

    @Test
    void obtenerPorVenta_DeberiaRetornarLista()
            throws Exception {

        DetalleVentaResponseDTO response =
                DetalleVentaResponseDTO.builder()
                        .id(1L)
                        .ventaId(1L)
                        .medicamentoId(2L)
                        .cantidad(5)
                        .precioUnitario(1000.0)
                        .build();

        when(service.obtenerPorVenta(1L))
                .thenReturn(List.of(response));

        mockMvc.perform(
                        get("/api/detalle-venta/venta/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id")
                        .value(1));
    }
}
