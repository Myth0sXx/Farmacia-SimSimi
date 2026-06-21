package com.farmacia.msrecetas.dto.response;

import com.farmacia.msrecetas.model.EstadoReceta;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecetaResponseDTO {
    private Long id;
    private Long clienteId;
    private String doctorNombre;
    private LocalDate fecha;
    private EstadoReceta estado;
}
