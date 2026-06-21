package com.farmacia.msnotificaciones.service.impl;

import com.farmacia.msnotificaciones.dto.request.NotificacionRequestDTO;
import com.farmacia.msnotificaciones.dto.response.NotificacionResponseDTO;
import com.farmacia.msnotificaciones.mapper.NotificacionMapper;
import com.farmacia.msnotificaciones.model.Notificacion;
import com.farmacia.msnotificaciones.repository.NotificacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificacionServiceImplTest {
    @Mock
    private NotificacionRepository repository;

    @Mock
    private NotificacionMapper mapper;

    @InjectMocks
    private NotificacionServiceImpl service;

    private NotificacionRequestDTO requestDTO;
    private Notificacion notificacionEntity;
    private NotificacionResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        requestDTO = new NotificacionRequestDTO();
        requestDTO.setMensaje("Stock mínimo alcanzado para Paracetamol");

        notificacionEntity = Notificacion.builder()
                .id(1L)
                .mensaje("Stock mínimo alcanzado para Paracetamol")
                .fecha(LocalDateTime.now())
                .build();

        responseDTO = new NotificacionResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setMensaje("Stock mínimo alcanzado para Paracetamol");
        responseDTO.setFecha(notificacionEntity.getFecha());
    }

    @Test
    @DisplayName("Debería guardar y retornar la notificación exitosamente")
    void enviarNotificacionExitoso() {
        // Arrange
        when(repository.save(any(Notificacion.class))).thenReturn(notificacionEntity);
        when(mapper.toDTO(any(Notificacion.class))).thenReturn(responseDTO);

        // Act
        NotificacionResponseDTO resultado = service.enviarNotificacion(requestDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Stock mínimo alcanzado para Paracetamol", resultado.getMensaje());
        verify(repository, times(1)).save(any(Notificacion.class));
        verify(mapper, times(1)).toDTO(any(Notificacion.class));
    }

    @Test
    @DisplayName("Debería retornar una lista con todas las notificaciones")
    void listarNotificacionesExitoso() {
        // Arrange
        when(repository.findAll()).thenReturn(List.of(notificacionEntity));
        when(mapper.toDTO(notificacionEntity)).thenReturn(responseDTO);

        // Act
        List<NotificacionResponseDTO> lista = service.listar();

        // Assert
        assertNotNull(lista);
        assertEquals(1, lista.size());
        assertEquals("Stock mínimo alcanzado para Paracetamol", lista.get(0).getMensaje());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando ocurre un error al guardar")
    void enviarNotificacionError() {

        when(repository.save(any(Notificacion.class)))
                .thenThrow(new RuntimeException("Error BD"));

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> service.enviarNotificacion(requestDTO)
        );

        assertEquals("Error BD", exception.getMessage());

        verify(repository, times(1))
                .save(any(Notificacion.class));
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando falla la consulta")
    void listarNotificacionesError() {

        when(repository.findAll())
                .thenThrow(new RuntimeException("Error BD"));

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> service.listar()
        );

        assertEquals("Error BD", exception.getMessage());

        verify(repository, times(1))
                .findAll();
    }
}