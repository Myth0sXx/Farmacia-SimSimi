package com.farmacia.msrecetas.dto.request;
import jakarta.validation.constraints.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RecetaRequestDTO {
    @NotNull(
            message = "El cliente es obligatorio"
    )
    private Long clienteId;
    @NotBlank(
            message = "El nombre del doctor es obligatorio"
    )
    @Size(
            max = 120,
            message = "El nombre del doctor no puede superar 120 caracteres"
    )
    private String doctorNombre;
}


