package com.farmacia.msnotificaciones.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NotificacionRequestDTO {

    @NotBlank(message = "El mensaje es obligatorio")
    @Size(max = 500, message = "Máximo 500 caracteres")
    private String mensaje;
}
