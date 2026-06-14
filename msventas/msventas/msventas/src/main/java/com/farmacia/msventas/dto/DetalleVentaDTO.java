package com.farmacia.msventas.dto;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DetalleVentaDTO {
    private Long ventaId;

    @NotNull(message = "El medicamento es obligatorio")
    private Long medicamentoId;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(
            value = 1,
            message = "La cantidad debe ser mayor a 0"
    )
    private Integer cantidad;

    @NotNull(message = "El precio unitario es obligatorio")
    @Positive(message = "El precio unitario debe ser mayor a 0")
    private Double precioUnitario;

    private Boolean requiereReceta;
}
