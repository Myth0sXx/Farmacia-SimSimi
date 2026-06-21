package com.farmacia.mspagos.mapper;
import com.farmacia.mspagos.dto.request.PagoRequestDTO;
import com.farmacia.mspagos.dto.response.PagoResponseDTO;
import com.farmacia.mspagos.model.EstadoPago;
import com.farmacia.mspagos.model.Pago;

import org.springframework.stereotype.Component;

@Component
public class PagoMapper {

    public Pago toEntity(
            PagoRequestDTO dto
    ) {

        Pago pago = new Pago();

        pago.setVentaId(
                dto.getVentaId()
        );

        pago.setMonto(dto.getMonto());

        pago.setMetodo(
                dto.getMetodo()
        );

        pago.setEstado(
                EstadoPago.APROBADO
        );

        return pago;
    }

    public PagoResponseDTO toDTO(
            Pago pago
    ) {

        return PagoResponseDTO.builder()
                .id(pago.getId())
                .ventaId(
                        pago.getVentaId()
                )
                .monto(
                        pago.getMonto()
                )
                .metodo(
                        pago.getMetodo()
                )
                .estado(
                        pago.getEstado()
                )
                .fecha(
                        pago.getFecha()
                )
                .build();
    }
}

