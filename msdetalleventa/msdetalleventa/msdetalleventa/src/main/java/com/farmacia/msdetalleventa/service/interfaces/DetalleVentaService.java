package com.farmacia.msdetalleventa.service.interfaces;

import com.farmacia.msdetalleventa.dto.request.DetalleVentaRequestDTO;
import com.farmacia.msdetalleventa.dto.response.DetalleVentaResponseDTO;

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
