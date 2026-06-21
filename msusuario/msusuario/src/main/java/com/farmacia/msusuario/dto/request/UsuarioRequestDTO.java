package com.farmacia.msusuario.dto.request;
import com.farmacia.msusuario.model.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(
            min = 2,
            max = 100,
            message = "El nombre debe tener entre 2 y 100 caracteres"
    )
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(
            min = 6,
            max = 60,
            message = "La contraseña debe tener entre 6 y 60 caracteres"
    )
    private String password;

    @NotNull(message = "El rol es obligatorio")
    private Rol rol;
}

