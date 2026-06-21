package com.farmacia.msusuario.dto.response;
import com.farmacia.msusuario.model.Rol;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioResponseDTO {
    private Long id;
    private String nombre;
    private String email;
    private Rol rol;
}
