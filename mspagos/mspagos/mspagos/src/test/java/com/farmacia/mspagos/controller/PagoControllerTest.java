package com.farmacia.mspagos.controller;
import com.farmacia.mspagos.dto.response.PagoResponseDTO;
import com.farmacia.mspagos.service.interfaces.PagoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import java.math.BigDecimal;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PagoController.class)
class PagoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PagoService pagoService;

    @Test
    void debeProcesarPago() throws Exception {

        PagoResponseDTO response = PagoResponseDTO.builder()
                .id(1L)
                .ventaId(100L)
                .monto(new BigDecimal("15000"))
                .build();

        when(pagoService.procesarPago(any())).thenReturn(response);

        mockMvc.perform(post("/api/pagos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "ventaId": 100,
                              "monto": 15000,
                              "metodo": "TARJETA"
                            }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.ventaId").value(100));
    }

    @Test
    void debeListarPagos() throws Exception {

        List<PagoResponseDTO> pagos = List.of(
                PagoResponseDTO.builder()
                        .id(1L)
                        .ventaId(100L)
                        .build()
        );

        when(pagoService.listarPagos()).thenReturn(pagos);

        mockMvc.perform(get("/api/pagos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].ventaId").value(100));
    }

    @Test
    void debeBuscarPorVenta() throws Exception {

        List<PagoResponseDTO> pagos = List.of(
                PagoResponseDTO.builder()
                        .id(1L)
                        .ventaId(100L)
                        .build()
        );

        when(pagoService.obtenerPorVenta(100L)).thenReturn(pagos);

        mockMvc.perform(get("/api/pagos/venta/100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].ventaId").value(100));
    }

    // 🔥 TEST DE VALIDACIÓN (IMPORTANTE PARA NOTA)
    @Test
    void debeFallarCuandoFaltaMonto() throws Exception {

        mockMvc.perform(post("/api/pagos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "ventaId": 100,
                              "metodo": "TARJETA"
                            }
                        """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void debeFallarCuandoMontoEsCero() throws Exception {

        mockMvc.perform(post("/api/pagos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "ventaId": 100,
                              "monto": 0,
                              "metodo": "TARJETA"
                            }
                        """))
                .andExpect(status().isBadRequest());
    }
}
