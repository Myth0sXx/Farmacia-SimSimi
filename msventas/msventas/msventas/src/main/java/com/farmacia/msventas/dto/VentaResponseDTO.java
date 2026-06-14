package com.farmacia.msventas.dto;
import com.farmacia.msventas.model.EstadoVenta;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaResponseDTO {
    private Long id;
    private Long clienteId;
    private LocalDateTime fecha;
    private Double total;
    private EstadoVenta estado;
}
