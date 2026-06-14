package com.farmacia.msrecetas.dto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidarRecetaDTO {

    @NotNull(
            message = "El cliente es obligatorio"
    )
    private Long clienteId;
}
