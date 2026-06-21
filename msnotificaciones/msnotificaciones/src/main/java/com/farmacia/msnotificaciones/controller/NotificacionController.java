package com.farmacia.msnotificaciones.controller;
import com.farmacia.msnotificaciones.dto.request.NotificacionRequestDTO;
import com.farmacia.msnotificaciones.dto.response.NotificacionResponseDTO;
import com.farmacia.msnotificaciones.service.interfaces.NotificacionService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
@Tag(name = "Notificaciones", description = "Operaciones sobre notificaciones")
public class NotificacionController {

    private final NotificacionService service;

    @PostMapping
    @Operation(summary = "Enviar notificación", description = "Envía una notificación y devuelve la entidad creada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Notificación creada"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public ResponseEntity<NotificacionResponseDTO> enviar(
            @Parameter(description = "Payload de notificación", required = true) @Valid @RequestBody NotificacionRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.enviarNotificacion(dto));
    }

    @GetMapping
    @Operation(summary = "Listar notificaciones", description = "Devuelve todas las notificaciones")
    @ApiResponse(responseCode = "200", description = "Lista de notificaciones")
    public ResponseEntity<List<NotificacionResponseDTO>> listar() {

        return ResponseEntity.ok(service.listar());
    }
}



