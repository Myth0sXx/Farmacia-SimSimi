package com.farmacia.msreportes.integration;
import com.farmacia.msreportes.dto.request.ReporteRequestDTO;
import com.farmacia.msreportes.repository.ReporteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ReporteIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ReporteRepository repository;

    @Test
    void crearReporteFlujoCompleto() throws Exception {

        ReporteRequestDTO request = new ReporteRequestDTO("REPORTE_VENTAS");

        mockMvc.perform(post("/api/v1/reportes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipo").value("REPORTE_VENTAS"));

        // verifica que realmente se guardó en BD
        assert(repository.findAll().size() > 0);
    }
}
