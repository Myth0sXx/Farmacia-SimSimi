package com.farmacia.mscliente.controller;
import com.farmacia.mscliente.dto.*;
import com.farmacia.mscliente.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> crear(
            @Valid @RequestBody ClienteRequestDTO dto
    ) {

        log.info("POST /api/clientes - Creando cliente");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.crear(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> obtener(
            @PathVariable Long id
    ) {

        log.info("GET /api/clientes/{}", id);

        return ResponseEntity.ok(
                service.obtenerPorId(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar() {

        log.info("GET /api/clientes - Listando clientes");

        return ResponseEntity.ok(
                service.listar()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequestDTO dto
    ) {

        log.info("PUT /api/clientes/{} - Actualizando cliente", id);

        return ResponseEntity.ok(
                service.actualizar(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id
    ) {

        log.info("DELETE /api/clientes/{} - Eliminando cliente", id);

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}

