package com.farmacia.msreportes.repository;

import com.farmacia.msreportes.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteRepository
        extends JpaRepository<Reporte, Long> {
}
