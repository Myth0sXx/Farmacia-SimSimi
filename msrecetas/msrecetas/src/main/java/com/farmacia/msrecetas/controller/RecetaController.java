package com.farmacia.msrecetas.controller;
import com.farmacia.msrecetas.dto.request.RecetaRequestDTO;
import com.farmacia.msrecetas.dto.response.RecetaResponseDTO;
import com.farmacia.msrecetas.dto.validar.ValidarRecetaDTO;
import com.farmacia.msrecetas.service.interfaces.RecetaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/recetas") // Cumple con las Buenas Prácticas de versionamiento exigidas
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

    // Ruta semántica corregida bajo el estándar /api/v1/recetas/validar
    @PostMapping("/validar")
    public ResponseEntity<Boolean> validar(
            @Valid @RequestBody ValidarRecetaDTO dto
    ) {
        return ResponseEntity.ok(service.validarReceta(dto));
    }

    // Ruta semántica corregida para acciones específicas sobre recursos individuales
    @PutMapping("/{id}/usar")
    public ResponseEntity<Void> usar(
            @PathVariable Long id
    ) {
        service.marcarComoUsada(id);
        return ResponseEntity.noContent().build(); // Cambiado a .noContent() (204) ya que es un método void sin retorno
    }
}