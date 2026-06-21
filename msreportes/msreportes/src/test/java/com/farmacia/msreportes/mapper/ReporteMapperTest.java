package com.farmacia.msreportes.mapper;
import com.farmacia.msreportes.dto.response.ReporteResponseDTO;
import com.farmacia.msreportes.model.Reporte;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReporteMapperTest {

    private final ReporteMapper mapper = new ReporteMapper();

    @Test
    @DisplayName("Mapeo correcto de Entity a DTO")
    void toDTO() {

        LocalDateTime now = LocalDateTime.now();

        Reporte reporte = Reporte.builder()
                .id(1L)
                .tipo("REPORTE_VENTAS")
                .contenido("[]")
                .generadoEn(now)
                .build();

        ReporteResponseDTO dto = mapper.toDTO(reporte);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("REPORTE_VENTAS", dto.getTipo());
        assertEquals("[]", dto.getContenido());
        assertEquals(now, dto.getGeneradoEn());
    }

    @Test
    @DisplayName("Mapper no debe romper con null (caso borde)")
    void toDTONull() {

        assertThrows(NullPointerException.class, () -> {
            mapper.toDTO(null);
        });
    }
}
