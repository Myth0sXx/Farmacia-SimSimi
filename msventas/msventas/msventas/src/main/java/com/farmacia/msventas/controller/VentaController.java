package com.farmacia.msventas.controller;
import com.farmacia.msventas.dto.*;
import com.farmacia.msventas.service.VentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VentaController {
    private final VentaService ventaService;

    @PostMapping
    public ResponseEntity<VentaResponseDTO> realizarVenta(
            @Valid
            @RequestBody
            VentaRequestDTO dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ventaService.realizarVenta(dto)
                );
    }
}
