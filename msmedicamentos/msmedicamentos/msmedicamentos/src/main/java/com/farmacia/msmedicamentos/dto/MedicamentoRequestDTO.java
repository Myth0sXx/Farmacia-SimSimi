package com.farmacia.msmedicamentos.dto;
import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class MedicamentoRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(
            max = 120,
            message = "El nombre no puede superar los 120 caracteres"
    )
    private String nombre;

    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private Double precio;

    @NotNull(message = "Debe indicar si requiere receta")
    private Boolean requiereReceta;
}
