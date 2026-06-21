package com.farmacia.msdetalleventa.controller;

import com.farmacia.msdetalleventa.dto.request.DetalleVentaRequestDTO;
import com.farmacia.msdetalleventa.dto.response.DetalleVentaResponseDTO;
import com.farmacia.msdetalleventa.service.interfaces.DetalleVentaService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-venta")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DetalleVentaController {

    private final DetalleVentaService service;

    @PostMapping
    public ResponseEntity<DetalleVentaResponseDTO>
    crear(
            @Valid
            @RequestBody
            DetalleVentaRequestDTO dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.crearDetalle(dto));
    }

    @PostMapping("/lote")
    public ResponseEntity<List<DetalleVentaResponseDTO>>
    crearMultiples(
            @Valid
            @RequestBody
            List<DetalleVentaRequestDTO> detalles
    ) {

        return ResponseEntity.ok(
                service.crearMultiples(detalles)
        );
    }

    @GetMapping("/venta/{ventaId}")
    public ResponseEntity<List<DetalleVentaResponseDTO>>
    obtenerPorVenta(
            @PathVariable Long ventaId
    ) {

        return ResponseEntity.ok(
                service.obtenerPorVenta(ventaId)
        );
    }
}

