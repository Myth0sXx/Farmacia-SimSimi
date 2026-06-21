package com.farmacia.msdetalleventa.mapper;

import com.farmacia.msdetalleventa.dto.request.DetalleVentaRequestDTO;
import com.farmacia.msdetalleventa.dto.response.DetalleVentaResponseDTO;
import com.farmacia.msdetalleventa.model.DetalleVenta;

import org.springframework.stereotype.Component;

@Component
public class DetalleVentaMapper {

    public DetalleVenta toEntity(
            DetalleVentaRequestDTO dto
    ) {

        DetalleVenta detalle =
                new DetalleVenta();

        detalle.setVentaId(
                dto.getVentaId()
        );

        detalle.setMedicamentoId(
                dto.getMedicamentoId()
        );

        detalle.setCantidad(
                dto.getCantidad()
        );

        detalle.setPrecioUnitario(
                dto.getPrecioUnitario()
        );

        return detalle;
    }

    public DetalleVentaResponseDTO toDTO(
            DetalleVenta detalle
    ) {

        return DetalleVentaResponseDTO.builder()
                .id(detalle.getId())
                .ventaId(
                        detalle.getVentaId()
                )
                .medicamentoId(
                        detalle.getMedicamentoId()
                )
                .cantidad(
                        detalle.getCantidad()
                )
                .precioUnitario(
                        detalle.getPrecioUnitario()
                )
                .build();
    }
}