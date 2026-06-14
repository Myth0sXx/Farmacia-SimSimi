package com.farmacia.msnotificaciones.service.impl;
import com.farmacia.msnotificaciones.mapper.NotificacionMapper;
import com.farmacia.msnotificaciones.dto.NotificacionRequestDTO;
import com.farmacia.msnotificaciones.dto.NotificacionResponseDTO;
import com.farmacia.msnotificaciones.model.Notificacion;
import com.farmacia.msnotificaciones.repository.NotificacionRepository;
import com.farmacia.msnotificaciones.service.NotificacionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class NotificacionServiceImpl implements NotificacionService {

    private final NotificacionRepository repository;
    private final NotificacionMapper mapper;

    @Override
    public NotificacionResponseDTO enviarNotificacion(NotificacionRequestDTO dto) {

        log.info("Enviando notificación: {}", dto.getMensaje());

        Notificacion notificacion = Notificacion.builder()
                .mensaje(dto.getMensaje())
                .build();

        Notificacion guardada = repository.save(notificacion);

        log.info("Notificación guardada con ID {}", guardada.getId());

        return mapper.toDTO(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificacionResponseDTO> listar() {

        log.info("Listando notificaciones");

        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }
}


