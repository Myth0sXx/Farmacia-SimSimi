package com.farmacia.msventas.dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class VentaRequestDTO {
    @NotNull(message = "El cliente es obligatorio")
    private Long clienteId;

    @Valid
    @NotEmpty(message = "La venta debe tener detalles")
    private List<DetalleVentaDTO> detalles;
}
