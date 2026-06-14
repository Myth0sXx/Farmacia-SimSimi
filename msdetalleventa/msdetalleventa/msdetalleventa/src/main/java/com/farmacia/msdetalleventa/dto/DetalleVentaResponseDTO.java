package com.farmacia.msdetalleventa.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleVentaResponseDTO {
    private Long id;
    private Long ventaId;
    private Long medicamentoId;
    private Integer cantidad;
    private Double precioUnitario;
}
