package com.farmacia.mspagos.dto.request;
import com.farmacia.mspagos.model.MetodoPago;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PagoRequestDTO {
    @NotNull(message = "La venta es obligatoria")
    private Long ventaId;

    @NotNull(message = "El monto es obligatorio")

    @Positive(message = "El monto debe ser mayor a 0")
    private BigDecimal monto;

    @NotNull(message = "El método de pago es obligatorio")
    private MetodoPago metodo;
}

