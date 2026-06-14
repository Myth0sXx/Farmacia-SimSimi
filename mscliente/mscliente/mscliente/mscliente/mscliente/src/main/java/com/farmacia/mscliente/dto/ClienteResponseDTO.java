package com.farmacia.mscliente.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteResponseDTO {
    private Long id;
    private String nombre;
    private String email;
    private String telefono;

}
