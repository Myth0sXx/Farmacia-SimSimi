package com.farmacia.msmedicamentos.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicamentoResponseDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Boolean requiereReceta;
}
