package com.farmacia.msnotificaciones.service;
import com.farmacia.msnotificaciones.dto.NotificacionRequestDTO;
import com.farmacia.msnotificaciones.dto.NotificacionResponseDTO;
import java.util.List;

public interface NotificacionService {
    NotificacionResponseDTO enviarNotificacion(NotificacionRequestDTO dto);
    List<NotificacionResponseDTO> listar();
}

