package com.farmacia.msinventario.controller;
import com.farmacia.msinventario.dto.stock.ActualizarStockDTO;
import com.farmacia.msinventario.dto.request.InventarioRequestDTO;
import com.farmacia.msinventario.dto.response.InventarioResponseDTO;
import com.farmacia.msinventario.service.interfaces.InventarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1/inventarios") // Plural y versionado
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService service;

    @PostMapping
    public ResponseEntity<InventarioResponseDTO> crearOActualizar(@Valid @RequestBody InventarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.crearOActualizar(dto));
    }

    @GetMapping("/medicamento/{medicamentoId}") // Claridad semántica en el parámetro
    public ResponseEntity<InventarioResponseDTO> obtenerPorMedicamento(@PathVariable Long medicamentoId) {
        return ResponseEntity.ok(service.obtenerPorMedicamentoId(medicamentoId));
    }

    @GetMapping
    public ResponseEntity<List<InventarioResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarInventario());
    }

    @PostMapping("/reducciones") // Sub-recurso semántico en lugar del verbo /descontar
    public ResponseEntity<Void> descontar(@Valid @RequestBody ActualizarStockDTO dto) {
        service.descontarStock(dto);
        return ResponseEntity.noContent().build(); // 244 No Content es ideal para operaciones de actualización sin retorno
    }
}

