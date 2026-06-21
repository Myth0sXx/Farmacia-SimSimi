package com.farmacia.msreportes.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ReporteRequestDTO {

    @NotBlank(message = "El tipo es obligatorio")
    private String tipo;

}
