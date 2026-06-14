package com.farmacia.msventas.mapper;

import com.farmacia.msventas.dto.VentaRequestDTO;
import com.farmacia.msventas.dto.VentaResponseDTO;
import com.farmacia.msventas.model.EstadoVenta;
import com.farmacia.msventas.model.Venta;

import org.springframework.stereotype.Component;

@Component
public class VentaMapper {

    public Venta toEntity(
            VentaRequestDTO dto
    ) {

        return Venta.builder()
                .clienteId(dto.getClienteId())
                .total(0.0)
                .estado(EstadoVenta.PENDIENTE)
                .build();
    }

    public VentaResponseDTO toDTO(
            Venta venta
    ) {

        return VentaResponseDTO.builder()
                .id(venta.getId())
                .clienteId(
                        venta.getClienteId()
                )
                .fecha(
                        venta.getFecha()
                )
                .total(
                        venta.getTotal()
                )
                .estado(
                        venta.getEstado()
                )
                .build();
    }
}