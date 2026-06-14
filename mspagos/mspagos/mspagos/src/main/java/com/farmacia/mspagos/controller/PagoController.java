package com.farmacia.mspagos.controller;
import com.farmacia.mspagos.dto.PagoRequestDTO;
import com.farmacia.mspagos.dto.PagoResponseDTO;
import com.farmacia.mspagos.service.PagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PagoController {
    private final PagoService pagoService;

    @PostMapping
    public ResponseEntity<PagoResponseDTO> procesar(
            @Valid @RequestBody PagoRequestDTO dto
    ) {

        return ResponseEntity.ok(
                pagoService.procesarPago(dto)
        );
    }

    @GetMapping
    public ResponseEntity<List<PagoResponseDTO>> listar() {

        return ResponseEntity.ok(
                pagoService.listarPagos()
        );
    }

    @GetMapping("/venta/{ventaId}")
    public ResponseEntity<List<PagoResponseDTO>> porVenta(
            @PathVariable Long ventaId
    ) {

        return ResponseEntity.ok(
                pagoService.obtenerPorVenta(ventaId)
        );
    }
}
