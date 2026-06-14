package com.farmacia.mspagos.repository;
import com.farmacia.mspagos.model.Pago;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long>{
    List<Pago> findByVentaId(Long ventaId);

}
