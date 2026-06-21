package com.farmacia.msreportes.service.interfaces;
import com.farmacia.msreportes.dto.response.ReporteResponseDTO;
import java.util.List;

public interface ReporteService {
    ReporteResponseDTO generarReporte(String tipo);
    List<ReporteResponseDTO> listarReportes();
    ReporteResponseDTO obtenerPorId(Long id);
}

