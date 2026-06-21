package com.farmacia.mspagos.service.impl;
import com.farmacia.mspagos.dto.request.PagoRequestDTO;
import com.farmacia.mspagos.dto.response.PagoResponseDTO;
import com.farmacia.mspagos.mapper.PagoMapper;
import java.math.BigDecimal;
import com.farmacia.mspagos.model.MetodoPago;
import com.farmacia.mspagos.model.Pago;
import com.farmacia.mspagos.repository.PagoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagoServiceImplTest {

    @Mock
    private PagoRepository pagoRepository;

    @Mock
    private PagoMapper pagoMapper;

    @InjectMocks
    private PagoServiceImpl pagoService;

    @Test
    void debeListarPagos() {

        Pago pago = new Pago();
        pago.setId(1L);
        pago.setVentaId(1L);

        PagoResponseDTO dto = PagoResponseDTO.builder()
                .id(1L)
                .ventaId(1L)
                .build();

        when(pagoRepository.findAll()).thenReturn(List.of(pago));
        when(pagoMapper.toDTO(pago)).thenReturn(dto);

        List<PagoResponseDTO> resultado = pagoService.listarPagos();

        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getId());

        verify(pagoRepository).findAll();
    }

    @Test
    void debeObtenerPorVenta() {

        Long ventaId = 10L;

        Pago pago = new Pago();
        pago.setVentaId(ventaId);

        PagoResponseDTO dto = PagoResponseDTO.builder()
                .ventaId(ventaId)
                .build();

        when(pagoRepository.findByVentaId(ventaId))
                .thenReturn(List.of(pago));

        when(pagoMapper.toDTO(pago))
                .thenReturn(dto);

        List<PagoResponseDTO> resultado =
                pagoService.obtenerPorVenta(ventaId);

        assertEquals(1, resultado.size());
        assertEquals(ventaId, resultado.get(0).getVentaId());

        verify(pagoRepository).findByVentaId(ventaId);
    }

    @Test
    void debeProcesarPago() {
        PagoRequestDTO request = new PagoRequestDTO();
        request.setVentaId(1L);
        request.setMonto(new BigDecimal("15000"));
        request.setMetodo(MetodoPago.TARJETA);

        Pago pago = new Pago();
        Pago guardado = new Pago();
        guardado.setId(1L);

        PagoResponseDTO response = PagoResponseDTO.builder()
                .id(1L)
                .ventaId(1L)
                .build();

        when(pagoMapper.toEntity(request)).thenReturn(pago);
        when(pagoRepository.save(pago)).thenReturn(guardado);
        when(pagoMapper.toDTO(guardado)).thenReturn(response);

        PagoResponseDTO resultado = pagoService.procesarPago(request);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());

        verify(pagoRepository).save(pago);
    }

    @Test
    void debeLanzarErrorSiRepositorioFalla() {

        PagoRequestDTO request = new PagoRequestDTO();
        request.setVentaId(1L);
        request.setMonto(new BigDecimal("15000"));
        request.setMetodo(MetodoPago.TARJETA);

        Pago pago = new Pago();

        when(pagoMapper.toEntity(request)).thenReturn(pago);
        when(pagoRepository.save(pago)).thenThrow(new RuntimeException("DB error"));

        assertThrows(RuntimeException.class, () -> {
            pagoService.procesarPago(request);
        });
    }
}