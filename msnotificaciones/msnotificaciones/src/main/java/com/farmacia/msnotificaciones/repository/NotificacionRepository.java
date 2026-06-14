package com.farmacia.msnotificaciones.repository;
import com.farmacia.msnotificaciones.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionRepository
        extends JpaRepository<Notificacion, Long> {
}

