package com.farmacia.msrecetas.controller;

import com.farmacia.msrecetas.dto.*;
import com.farmacia.msrecetas.service.RecetaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recetas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RecetaController {

    private final RecetaService service;

    @PostMapping
    public ResponseEntity<RecetaResponseDTO> crear(
            @Valid @RequestBody RecetaRequestDTO dto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.crearReceta(dto));
    }

    @GetMapping
    public ResponseEntity<List<RecetaResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarRecetas());
    }

    @PostMapping("/validar")
    public ResponseEntity<Boolean> validar(
            @Valid @RequestBody ValidarRecetaDTO dto
    ) {
        return ResponseEntity.ok(service.validarReceta(dto));
    }

    @PutMapping("/{id}/usar")
    public ResponseEntity<Void> usar(
            @PathVariable Long id
    ) {
        service.marcarComoUsada(id);
        return ResponseEntity.ok().build();
    }
}