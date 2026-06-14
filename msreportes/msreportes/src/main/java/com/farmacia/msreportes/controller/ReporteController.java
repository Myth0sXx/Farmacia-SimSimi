package com.farmacia.msreportes.controller;
import com.farmacia.msreportes.dto.ReporteResponseDTO;
import com.farmacia.msreportes.service.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService service;

    @PostMapping("/ventas")
    public ResponseEntity<ReporteResponseDTO> generarReporteVentas() {
        return ResponseEntity.ok(service.generarReporteVentas());
    }

    @GetMapping
    public ResponseEntity<List<ReporteResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarReportes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteResponseDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }
}


