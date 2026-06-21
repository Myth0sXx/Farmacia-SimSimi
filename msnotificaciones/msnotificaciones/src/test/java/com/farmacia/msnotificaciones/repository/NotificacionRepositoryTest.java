package com.farmacia.msnotificaciones.repository;
import com.farmacia.msnotificaciones.model.Notificacion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class NotificacionRepositoryTest {

    @Autowired
    private NotificacionRepository repository;

    @Test
    @DisplayName("Debe guardar una notificación correctamente")
    void guardarNotificacion() {

        Notificacion notificacion = Notificacion.builder()
                .mensaje("Alerta de stock")
                .fecha(LocalDateTime.now())
                .build();

        Notificacion guardada = repository.save(notificacion);

        assertNotNull(guardada.getId());
        assertEquals("Alerta de stock", guardada.getMensaje());
    }

    @Test
    @DisplayName("Debe listar todas las notificaciones")
    void listarNotificaciones() {

        repository.save(
                Notificacion.builder()
                        .mensaje("Notificación 1")
                        .fecha(LocalDateTime.now())
                        .build()
        );

        List<Notificacion> lista = repository.findAll();

        assertFalse(lista.isEmpty());
    }
}