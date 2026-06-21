package com.farmacia.msdetalleventa.repository;
import com.farmacia.msdetalleventa.model.DetalleVenta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@DataJpaTest
class DetalleVentaRepositoryTest {

    @Autowired
    private DetalleVentaRepository repository;

    @Test
    void findByVentaId_DeberiaRetornarDetalles() {

        DetalleVenta detalle = DetalleVenta.builder()
                .ventaId(1L)
                .medicamentoId(2L)
                .cantidad(3)
                .precioUnitario(1000.0)
                .build();

        repository.save(detalle);

        List<DetalleVenta> resultado =
                repository.findByVentaId(1L);

        assertFalse(resultado.isEmpty());
        assertEquals(1L,
                resultado.getFirst().getVentaId());
    }
}

