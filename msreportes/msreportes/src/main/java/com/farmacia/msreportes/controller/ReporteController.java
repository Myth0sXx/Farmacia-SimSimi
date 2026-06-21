package com.farmacia.msreportes.controller;
import com.farmacia.msreportes.dto.request.ReporteRequestDTO;
import com.farmacia.msreportes.dto.response.ReporteResponseDTO;
import com.farmacia.msreportes.service.interfaces.ReporteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService service;

    @PostMapping
    public ResponseEntity<ReporteResponseDTO> generar(@RequestBody ReporteRequestDTO request) {
        return ResponseEntity.ok(service.generarReporte(request.getTipo()));
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


