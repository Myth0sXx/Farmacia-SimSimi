package com.farmacia.msreportes.service.impl;
import com.farmacia.msreportes.client.VentaClient;
import com.farmacia.msreportes.dto.response.ReporteResponseDTO;
import com.farmacia.msreportes.mapper.ReporteMapper;
import com.farmacia.msreportes.model.Reporte;
import com.farmacia.msreportes.repository.ReporteRepository;
import com.farmacia.msreportes.service.interfaces.ReporteService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.farmacia.msreportes.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ReporteServiceImpl implements ReporteService {

    private final ReporteRepository repository;
    private final ReporteMapper mapper;
    private final VentaClient ventaClient;

    @Override
    @CircuitBreaker(name = "ventaService", fallbackMethod = "fallbackGenerarReporte")
    public ReporteResponseDTO generarReporte(String tipo) {

        log.info("Generando reporte tipo: {}", tipo);

        String contenido = ventaClient.obtenerVentas();

        Reporte reporte = Reporte.builder()
                .tipo(tipo)
                .contenido(contenido)
                .build();

        Reporte guardado = repository.save(reporte);

        return mapper.toDTO(guardado);
    }

    // 🔥 FALLBACK (si ms-ventas falla)
    public ReporteResponseDTO fallbackGenerarReporte(String tipo, Throwable ex) {

        log.warn("Fallback activado. ms-ventas no disponible: {}", ex.getMessage());

        Reporte reporte = Reporte.builder()
                .tipo(tipo)
                .contenido("[]") // respuesta segura
                .build();

        Reporte guardado = repository.save(reporte);

        return mapper.toDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public java.util.List<ReporteResponseDTO> listarReportes() {

        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public ReporteResponseDTO obtenerPorId(Long id) {

        Reporte reporte = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reporte no encontrado"));

        return mapper.toDTO(reporte);
    }
}