package com.farmacia.msmedicamentos.repository;
import com.farmacia.msmedicamentos.model.Medicamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
    List<Medicamento> findByNombreContainingIgnoreCase(String nombre);
}
