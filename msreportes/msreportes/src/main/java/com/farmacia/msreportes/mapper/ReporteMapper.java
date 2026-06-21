package com.farmacia.msreportes.mapper;

import com.farmacia.msreportes.dto.response.ReporteResponseDTO;
import com.farmacia.msreportes.model.Reporte;
import org.springframework.stereotype.Component;

@Component
public class ReporteMapper {

    public ReporteResponseDTO toDTO(Reporte reporte) {
        ReporteResponseDTO dto = new ReporteResponseDTO();
        dto.setId(reporte.getId());
        dto.setTipo(reporte.getTipo());
        dto.setGeneradoEn(reporte.getGeneradoEn());
        dto.setContenido(reporte.getContenido());
        return dto;
    }
}


