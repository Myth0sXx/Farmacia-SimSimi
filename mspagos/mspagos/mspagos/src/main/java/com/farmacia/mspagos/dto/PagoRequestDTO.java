package com.farmacia.mspagos.dto;
import com.farmacia.mspagos.model.MetodoPago;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PagoRequestDTO {
    @NotNull(message = "La venta es obligatoria")
    private Long ventaId;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "1.0",
            message = "El monto debe ser mayor a 0")
    private Double monto;

    @NotNull(message = "El método de pago es obligatorio")
    private MetodoPago metodo;
}
