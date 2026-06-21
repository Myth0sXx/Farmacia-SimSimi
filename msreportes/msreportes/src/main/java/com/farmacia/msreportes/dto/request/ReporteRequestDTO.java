package com.farmacia.msreportes.dto.request;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReporteRequestDTO {
    @NotBlank(message = "El tipo es obligatorio")
    private String tipo;
}
