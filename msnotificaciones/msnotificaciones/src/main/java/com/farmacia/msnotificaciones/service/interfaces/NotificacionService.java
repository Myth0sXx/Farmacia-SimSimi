package com.farmacia.msnotificaciones.service.interfaces;

import com.farmacia.msnotificaciones.dto.request.NotificacionRequestDTO;
import com.farmacia.msnotificaciones.dto.response.NotificacionResponseDTO;
import java.util.List;

public interface NotificacionService {
    NotificacionResponseDTO enviarNotificacion(NotificacionRequestDTO dto);
    List<NotificacionResponseDTO> listar();
}


