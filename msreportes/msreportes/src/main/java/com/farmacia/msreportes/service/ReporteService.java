package com.farmacia.msreportes.service;
import com.farmacia.msreportes.dto.ReporteResponseDTO;
import java.util.List;
public interface ReporteService {
    ReporteResponseDTO generarReporteVentas();
    List<ReporteResponseDTO> listarReportes();
    ReporteResponseDTO obtenerPorId(Long id);
}

