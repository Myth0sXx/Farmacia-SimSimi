package com.farmacia.msinventario.controller;
import com.farmacia.msinventario.dto.ActualizarStockDTO;
import com.farmacia.msinventario.dto.InventarioRequestDTO;
import com.farmacia.msinventario.dto.InventarioResponseDTO;
import com.farmacia.msinventario.service.InventarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventario")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService service;

    @PostMapping
    public ResponseEntity<InventarioResponseDTO> crear(@Valid @RequestBody InventarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.crearOActualizar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioResponseDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorMedicamentoId(id));
    }

    @GetMapping
    public ResponseEntity<List<InventarioResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarInventario());
    }

    @PostMapping("/descontar")
    public ResponseEntity<Void> descontar(@Valid @RequestBody ActualizarStockDTO dto) {
        service.descontarStock(dto);
        return ResponseEntity.ok().build();
    }
}


