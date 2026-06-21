package com.farmacia.msreportes.repository;
import com.farmacia.msreportes.model.Reporte;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReporteRepositoryTest {

    @Autowired
    private ReporteRepository repository;

    @Test
    @DisplayName("Guardar reporte")
    void save() {

        Reporte reporte = Reporte.builder()
                .tipo("TEST")
                .contenido("DATA")
                .generadoEn(LocalDateTime.now())
                .build();

        Reporte saved = repository.save(reporte);

        assertNotNull(saved.getId());
        assertEquals("TEST", saved.getTipo());
    }

    @Test
    @DisplayName("Buscar por ID")
    void findById() {

        Reporte saved = repository.save(
                Reporte.builder()
                        .tipo("R1")
                        .contenido("X")
                        .generadoEn(LocalDateTime.now())
                        .build()
        );

        Optional<Reporte> result = repository.findById(saved.getId());

        assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("Listar todos")
    void findAll() {

        repository.save(Reporte.builder()
                .tipo("R1")
                .contenido("A")
                .generadoEn(LocalDateTime.now())
                .build());

        List<Reporte> list = repository.findAll();

        assertFalse(list.isEmpty());
    }
}

