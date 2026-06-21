package com.farmacia.mspagos.repository;
import com.farmacia.mspagos.model.EstadoPago;
import com.farmacia.mspagos.model.MetodoPago;
import com.farmacia.mspagos.model.Pago;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@ActiveProfiles("test")
class PagoRepositoryTest {

    @Autowired
    private PagoRepository pagoRepository;

    @Test
    void debeGuardarYBuscarPorVentaId() {

        Pago pago = Pago.builder()
                .ventaId(1L)
                .monto(new BigDecimal("15000"))
                .metodo(MetodoPago.TARJETA)
                .estado(EstadoPago.APROBADO)
                .build();

        Pago guardado = pagoRepository.save(pago);

        List<Pago> resultado = pagoRepository.findByVentaId(1L);

        assertFalse(resultado.isEmpty());
        assertEquals(1L, resultado.get(0).getVentaId());
        assertEquals(new BigDecimal("15000"), resultado.get(0).getMonto());
    }
}
