package com.farmacia.msventas.client;

import com.farmacia.msventas.dto.NotificacionDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Hidden;

@FeignClient(
        name = "msnotificaciones",
        url = "http://localhost:8087"
)
@Hidden
public interface NotificacionClient {

    @PostMapping("/api/notificaciones")
    Void enviarNotificacion(
            @RequestBody NotificacionDTO dto
    );
}
