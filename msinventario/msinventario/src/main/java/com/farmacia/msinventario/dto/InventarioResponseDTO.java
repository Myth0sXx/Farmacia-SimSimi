package com.farmacia.msinventario.dto;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventarioResponseDTO {

    private Long id;
    private Long medicamentoId;
    private Integer cantidad;
    private LocalDateTime actualizadoEn;
}
