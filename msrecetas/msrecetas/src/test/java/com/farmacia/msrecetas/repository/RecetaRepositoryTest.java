package com.farmacia.msrecetas.repository;

import com.farmacia.msrecetas.model.EstadoReceta;
import com.farmacia.msrecetas.model.Receta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RecetaRepositoryTest {

    @Autowired
    private RecetaRepository repository;

    @Test
    @DisplayName("Debe encontrar recetas activas por cliente")
    void findByClienteIdAndEstado() {

        Receta receta = Receta.builder()
                .clienteId(100L)
                .doctorNombre("Dr. Test")
                .estado(EstadoReceta.ACTIVA)
                .build();

        repository.save(receta);

        List<Receta> resultado =
                repository.findByClienteIdAndEstado(
                        100L,
                        EstadoReceta.ACTIVA
                );

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals(100L, resultado.get(0).getClienteId());
        assertEquals(EstadoReceta.ACTIVA, resultado.get(0).getEstado());
    }
}