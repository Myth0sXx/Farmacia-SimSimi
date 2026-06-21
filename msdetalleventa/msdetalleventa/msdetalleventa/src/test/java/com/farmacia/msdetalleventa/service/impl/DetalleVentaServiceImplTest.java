package com.farmacia.msdetalleventa.service.impl;
import com.farmacia.msdetalleventa.dto.request.DetalleVentaRequestDTO;
import com.farmacia.msdetalleventa.dto.response.DetalleVentaResponseDTO;
import com.farmacia.msdetalleventa.mapper.DetalleVentaMapper;
import com.farmacia.msdetalleventa.model.DetalleVenta;
import com.farmacia.msdetalleventa.repository.DetalleVentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DetalleVentaServiceImplTest {

    @Mock
    private DetalleVentaRepository repository;

    @Mock
    private DetalleVentaMapper mapper;

    @InjectMocks
    private DetalleVentaServiceImpl service;

    private DetalleVentaRequestDTO requestDTO;
    private DetalleVenta detalle;
    private DetalleVentaResponseDTO responseDTO;

    @BeforeEach
    void setUp() {

        requestDTO = new DetalleVentaRequestDTO();
        requestDTO.setVentaId(1L);
        requestDTO.setMedicamentoId(2L);
        requestDTO.setCantidad(5);
        requestDTO.setPrecioUnitario(1000.0);

        detalle = DetalleVenta.builder()
                .id(1L)
                .ventaId(1L)
                .medicamentoId(2L)
                .cantidad(5)
                .precioUnitario(1000.0)
                .build();

        responseDTO = DetalleVentaResponseDTO.builder()
                .id(1L)
                .ventaId(1L)
                .medicamentoId(2L)
                .cantidad(5)
                .precioUnitario(1000.0)
                .build();
    }

    @Test
    void crearDetalle_DeberiaGuardarYRetornarDTO() {

        when(mapper.toEntity(requestDTO)).thenReturn(detalle);
        when(repository.save(detalle)).thenReturn(detalle);
        when(mapper.toDTO(detalle)).thenReturn(responseDTO);

        DetalleVentaResponseDTO resultado =
                service.crearDetalle(requestDTO);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());

        verify(repository).save(detalle);
    }

    @Test
    void crearMultiples_DeberiaGuardarLista() {

        List<DetalleVentaRequestDTO> lista =
                List.of(requestDTO);

        when(mapper.toEntity(any()))
                .thenReturn(detalle);

        when(repository.saveAll(anyList()))
                .thenReturn(List.of(detalle));

        when(mapper.toDTO(detalle))
                .thenReturn(responseDTO);

        List<DetalleVentaResponseDTO> resultado =
                service.crearMultiples(lista);

        assertEquals(1, resultado.size());

        verify(repository)
                .saveAll(anyList());
    }

    @Test
    void obtenerPorVenta_DeberiaRetornarLista() {

        when(repository.findByVentaId(1L))
                .thenReturn(List.of(detalle));

        when(mapper.toDTO(detalle))
                .thenReturn(responseDTO);

        List<DetalleVentaResponseDTO> resultado =
                service.obtenerPorVenta(1L);

        assertEquals(1, resultado.size());

        verify(repository)
                .findByVentaId(1L);
    }

}

