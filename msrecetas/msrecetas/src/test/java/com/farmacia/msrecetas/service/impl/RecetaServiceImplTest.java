package com.farmacia.msrecetas.service.impl;
import com.farmacia.msrecetas.dto.request.RecetaRequestDTO;
import com.farmacia.msrecetas.dto.response.RecetaResponseDTO;
import com.farmacia.msrecetas.dto.validar.ValidarRecetaDTO;
import com.farmacia.msrecetas.exception.ResourceNotFoundException;
import com.farmacia.msrecetas.mapper.RecetaMapper;
import com.farmacia.msrecetas.model.EstadoReceta;
import com.farmacia.msrecetas.model.Receta;
import com.farmacia.msrecetas.repository.RecetaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecetaServiceImplTest {

    @Mock
    private RecetaRepository repository;

    @Mock
    private RecetaMapper mapper;

    @InjectMocks
    private RecetaServiceImpl service;

    private Receta recetaMock;
    private RecetaResponseDTO responseDTOMock;

    @BeforeEach
    void setUp() {
        recetaMock = Receta.builder()
                .id(1L)
                .clienteId(100L)
                .doctorNombre("Dr. Simi")
                .fecha(LocalDate.now())
                .estado(EstadoReceta.ACTIVA)
                .build();

        responseDTOMock = RecetaResponseDTO.builder()
                .id(1L)
                .clienteId(100L)
                .doctorNombre("Dr. Simi")
                .fecha(LocalDate.now())
                .estado(EstadoReceta.ACTIVA)
                .build();
    }

    @Test
    @DisplayName("Debería crear una receta exitosamente")
    void crearRecetaExitosamente() {
        // Arrange
        RecetaRequestDTO requestDTO = new RecetaRequestDTO(100L, "Dr. Simi");
        when(repository.save(any(Receta.class))).thenReturn(recetaMock);
        when(mapper.toDTO(any(Receta.class))).thenReturn(responseDTOMock);

        // Act
        RecetaResponseDTO resultado = service.crearReceta(requestDTO);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Dr. Simi", resultado.getDoctorNombre());
        verify(repository, times(1)).save(any(Receta.class));
    }

    @Test
    @DisplayName("Debería lanzar ResourceNotFoundException al usar receta inexistente")
    void marcarComoUsadaLanzaExcepcion() {
        // Arrange
        when(repository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            service.marcarComoUsada(999L);
        });

        verify(repository, never()).save(any(Receta.class));
    }
    @Test
    @DisplayName("Debe listar recetas")
    void listarRecetasExitosamente() {

        when(repository.findAll())
                .thenReturn(List.of(recetaMock));

        when(mapper.toDTO(recetaMock))
                .thenReturn(responseDTOMock);

        List<RecetaResponseDTO> resultado =
                service.listarRecetas();

        assertEquals(1, resultado.size());

        verify(repository).findAll();
    }

    @Test
    @DisplayName("Debe validar receta activa")
    void validarRecetaExitosamente() {

        ValidarRecetaDTO dto =
                new ValidarRecetaDTO(100L);

        when(repository.findByClienteIdAndEstado(
                100L,
                EstadoReceta.ACTIVA))
                .thenReturn(List.of(recetaMock));

        boolean resultado =
                service.validarReceta(dto);

        assertTrue(resultado);
    }

    @Test
    @DisplayName("Debe marcar receta como usada")
    void marcarComoUsadaExitosamente() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(recetaMock));

        service.marcarComoUsada(1L);

        assertEquals(
                EstadoReceta.USADA,
                recetaMock.getEstado()
        );

        verify(repository).save(recetaMock);
    }
}
