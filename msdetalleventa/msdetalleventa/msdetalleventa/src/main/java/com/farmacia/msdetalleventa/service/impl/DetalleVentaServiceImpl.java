package com.farmacia.msdetalleventa.service.impl;

import com.farmacia.msdetalleventa.dto.DetalleVentaRequestDTO;
import com.farmacia.msdetalleventa.dto.DetalleVentaResponseDTO;
import com.farmacia.msdetalleventa.mapper.DetalleVentaMapper;
import com.farmacia.msdetalleventa.model.DetalleVenta;
import com.farmacia.msdetalleventa.repository.DetalleVentaRepository;
import com.farmacia.msdetalleventa.service.DetalleVentaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class DetalleVentaServiceImpl
        implements DetalleVentaService {

    private final DetalleVentaRepository repository;

    private final DetalleVentaMapper mapper;

    @Override
    public DetalleVentaResponseDTO crearDetalle(
            DetalleVentaRequestDTO dto
    ) {

        log.info(
                "Creando detalle de venta para venta {}",
                dto.getVentaId()
        );

        DetalleVenta detalle =
                mapper.toEntity(dto);

        DetalleVenta guardado =
                repository.save(detalle);

        log.info(
                "Detalle de venta creado con id {}",
                guardado.getId()
        );

        return mapper.toDTO(guardado);
    }

    @Override
    public List<DetalleVentaResponseDTO>
    crearMultiples(
            List<DetalleVentaRequestDTO> detallesDTO
    ) {

        log.info(
                "Guardando lote de detalles. Cantidad: {}",
                detallesDTO.size()
        );

        List<DetalleVenta> detalles =
                detallesDTO
                        .stream()
                        .map(mapper::toEntity)
                        .toList();

        List<DetalleVenta> guardados =
                repository.saveAll(detalles);

        log.info(
                "Detalles guardados correctamente"
        );

        return guardados.stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleVentaResponseDTO>
    obtenerPorVenta(
            Long ventaId
    ) {

        log.info(
                "Buscando detalles de venta {}",
                ventaId
        );

        return repository.findByVentaId(ventaId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }
}