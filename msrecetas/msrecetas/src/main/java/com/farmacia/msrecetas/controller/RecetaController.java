package com.farmacia.msrecetas.controller;

import com.farmacia.msrecetas.dto.request.RecetaRequestDTO;
import com.farmacia.msrecetas.dto.response.RecetaResponseDTO;
import com.farmacia.msrecetas.dto.validar.ValidarRecetaDTO;

import com.farmacia.msrecetas.service.intefaces.RecetaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Service
@RestController
@RequestMapping("/api/v1/recetas")
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
        return ResponseEntity.noContent().build();
    }
}