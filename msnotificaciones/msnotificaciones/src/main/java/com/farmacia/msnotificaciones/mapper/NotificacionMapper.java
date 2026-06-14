package com.farmacia.msnotificaciones.mapper;
import com.farmacia.msnotificaciones.dto.NotificacionResponseDTO;
import com.farmacia.msnotificaciones.model.Notificacion;
import org.springframework.stereotype.Component;
@Component
public class NotificacionMapper {

    public NotificacionResponseDTO toDTO(Notificacion notificacion) {
        NotificacionResponseDTO dto = new NotificacionResponseDTO();
        dto.setId(notificacion.getId());
        dto.setMensaje(notificacion.getMensaje());
        dto.setFecha(notificacion.getFecha());
        return dto;
    }
}
