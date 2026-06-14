package com.farmacia.msdetalleventa.service;

import com.farmacia.msdetalleventa.dto.*;

import java.util.List;

public interface DetalleVentaService {

    DetalleVentaResponseDTO crearDetalle(
            DetalleVentaRequestDTO dto
    );

    List<DetalleVentaResponseDTO> crearMultiples(
            List<DetalleVentaRequestDTO> detalles
    );

    List<DetalleVentaResponseDTO> obtenerPorVenta(
            Long ventaId
    );
}