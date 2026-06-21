package com.farmacia.msnotificaciones.dto.response;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NotificacionResponseDTO {

    private Long id;
    private String mensaje;
    private LocalDateTime fecha;
}
