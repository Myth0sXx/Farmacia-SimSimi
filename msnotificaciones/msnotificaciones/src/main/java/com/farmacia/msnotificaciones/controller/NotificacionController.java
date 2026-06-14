package com.farmacia.msnotificaciones.controller;
import com.farmacia.msnotificaciones.dto.NotificacionRequestDTO;
import com.farmacia.msnotificaciones.dto.NotificacionResponseDTO;
import com.farmacia.msnotificaciones.service.NotificacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService service;

    @PostMapping
    public ResponseEntity<NotificacionResponseDTO> enviar(
            @Valid @RequestBody NotificacionRequestDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.enviarNotificacion(dto));
    }

    @GetMapping
    public ResponseEntity<List<NotificacionResponseDTO>> listar() {

        return ResponseEntity.ok(service.listar());
    }
}


