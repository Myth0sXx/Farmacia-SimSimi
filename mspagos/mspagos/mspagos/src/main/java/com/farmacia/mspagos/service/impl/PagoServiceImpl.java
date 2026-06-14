package com.farmacia.mspagos.service.impl;
import com.farmacia.mspagos.dto.PagoRequestDTO;
import com.farmacia.mspagos.dto.PagoResponseDTO;

import com.farmacia.mspagos.mapper.PagoMapper;

import com.farmacia.mspagos.model.Pago;

import com.farmacia.mspagos.repository.PagoRepository;

import com.farmacia.mspagos.service.PagoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PagoServiceImpl
        implements PagoService {

    private final PagoRepository pagoRepository;

    private final PagoMapper mapper;

    @Override
    public PagoResponseDTO procesarPago(
            PagoRequestDTO dto
    ) {

        log.info(
                "Procesando pago para venta {}",
                dto.getVentaId()
        );

        Pago pago =
                mapper.toEntity(dto);

        log.info(
                "Pago aprobado para venta {}",
                dto.getVentaId()
        );

        Pago guardado =
                pagoRepository.save(pago);

        log.info(
                "Pago guardado con id {}",
                guardado.getId()
        );

        return mapper.toDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PagoResponseDTO> listarPagos() {

        log.info(
                "Listando pagos"
        );

        return pagoRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PagoResponseDTO>
    obtenerPorVenta(
            Long ventaId
    ) {

        log.info(
                "Buscando pagos de venta {}",
                ventaId
        );

        return pagoRepository.findByVentaId(ventaId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }
}