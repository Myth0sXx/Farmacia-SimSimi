package com.farmacia.msreportes.service.impl;
import com.farmacia.msreportes.client.VentaClient;
import com.farmacia.msreportes.dto.response.ReporteResponseDTO;
import com.farmacia.msreportes.exception.ResourceNotFoundException;
import com.farmacia.msreportes.mapper.ReporteMapper;
import com.farmacia.msreportes.model.Reporte;
import com.farmacia.msreportes.repository.ReporteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReporteServiceImplTest {

    @Mock
    private ReporteRepository repository;

    @Mock
    private ReporteMapper mapper;

    @Mock
    private VentaClient ventaClient;

    @InjectMocks
    private ReporteServiceImpl service;

    private Reporte reporteMock;
    private ReporteResponseDTO dtoMock;

    @BeforeEach
    void setUp() {

        reporteMock = Reporte.builder()
                .id(1L)
                .tipo("REPORTE_VENTAS")
                .contenido("[]")
                .generadoEn(LocalDateTime.now())
                .build();

        dtoMock = new ReporteResponseDTO(
                1L,
                "REPORTE_VENTAS",
                reporteMock.getGeneradoEn(),
                "[]"
        );
    }

    @Test
    void generarReporte() {

        when(ventaClient.obtenerVentas()).thenReturn("[]");
        when(repository.save(any())).thenReturn(reporteMock);
        when(mapper.toDTO(any())).thenReturn(dtoMock);

        ReporteResponseDTO result =
                service.generarReporte("REPORTE_VENTAS");

        assertNotNull(result);

        verify(repository).save(any());
        verify(ventaClient).obtenerVentas();
    }

    @Test
    void listarReportes() {

        when(repository.findAll()).thenReturn(List.of(reporteMock));
        when(mapper.toDTO(reporteMock)).thenReturn(dtoMock);

        List<ReporteResponseDTO> result =
                service.listarReportes();

        assertEquals(1, result.size());
    }

    @Test
    void obtenerPorId() {

        when(repository.findById(1L))
                .thenReturn(Optional.of(reporteMock));

        when(mapper.toDTO(reporteMock))
                .thenReturn(dtoMock);

        ReporteResponseDTO result =
                service.obtenerPorId(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void obtenerPorIdFail() {

        when(repository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.obtenerPorId(99L)
        );
    }
}

