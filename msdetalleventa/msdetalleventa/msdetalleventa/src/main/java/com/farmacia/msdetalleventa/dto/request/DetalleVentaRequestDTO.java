package com.farmacia.msdetalleventa.dto.request;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DetalleVentaRequestDTO {

    @NotNull(message = "La venta es obligatoria")
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
    @Positive(message = "El precio debe ser mayor a 0")
    private Double precioUnitario;
}
