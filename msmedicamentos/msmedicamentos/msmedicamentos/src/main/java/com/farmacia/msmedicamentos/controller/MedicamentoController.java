package com.farmacia.msmedicamentos.controller;
import com.farmacia.msmedicamentos.dto.*;
import com.farmacia.msmedicamentos.service.MedicamentoService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MedicamentoController {
    private final MedicamentoService service;

    @PostMapping
    public ResponseEntity<MedicamentoResponseDTO> crear(
            @Valid @RequestBody MedicamentoRequestDTO dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<MedicamentoResponseDTO>> listar() {

        return ResponseEntity.ok(
                service.listar()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoResponseDTO> obtener(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                service.obtenerPorId(id)
        );
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<MedicamentoResponseDTO>> buscar(
            @RequestParam String nombre
    ) {

        return ResponseEntity.ok(
                service.buscarPorNombre(nombre)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody MedicamentoRequestDTO dto
    ) {

        return ResponseEntity.ok(
                service.actualizar(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id
    ) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
