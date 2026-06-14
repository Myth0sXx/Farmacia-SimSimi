package com.farmacia.msreportes.service.impl;
import com.farmacia.msreportes.client.VentaClient;
import com.farmacia.msreportes.mapper.ReporteMapper;
import com.farmacia.msreportes.dto.ReporteResponseDTO;
import com.farmacia.msreportes.exception.ResourceNotFoundException;
import com.farmacia.msreportes.model.Reporte;
import com.farmacia.msreportes.repository.ReporteRepository;
import com.farmacia.msreportes.service.ReporteService;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ReporteServiceImpl implements ReporteService {

    private final ReporteRepository repository;
    private final ReporteMapper mapper;
    private final VentaClient ventaClient;

    @Override
    public ReporteResponseDTO generarReporteVentas() {

        log.info("Solicitando ventas a msventas...");

        String ventas = ventaClient.obtenerVentas();

        log.info("Ventas recibidas: {}", ventas);

        Reporte reporte = Reporte.builder()
                .tipo("REPORTE_VENTAS")
                .contenido(ventas)
                .build();

        Reporte guardado = repository.save(reporte);

        log.info("Reporte creado con ID {}", guardado.getId());

        return mapper.toDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReporteResponseDTO> listarReportes() {

        log.info("Listando reportes");

        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public ReporteResponseDTO obtenerPorId(Long id) {

        log.info("Buscando reporte ID {}", id);

        Reporte reporte = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Reporte no encontrado")
                );

        return mapper.toDTO(reporte);
    }
}

