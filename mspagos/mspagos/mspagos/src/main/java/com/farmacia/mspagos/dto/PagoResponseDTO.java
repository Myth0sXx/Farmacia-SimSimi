package com.farmacia.mspagos.dto;
import com.farmacia.mspagos.model.EstadoPago;
import com.farmacia.mspagos.model.MetodoPago;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PagoResponseDTO {
    private Long id;
    private Long ventaId;
    private Double monto;
    private MetodoPago metodo;
    private EstadoPago estado;
    private LocalDateTime fecha;
}
