package com.farmacia.msreportes.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReporteResponseDTO {
    private Long id;
    private String tipo;
    private LocalDateTime generadoEn;
    private String contenido;
}
