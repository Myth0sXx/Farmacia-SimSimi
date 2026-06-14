package com.farmacia.mscliente.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequestDTO {

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

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(
            regexp = "^(\\+56)?9[0-9]{8}$",
            message = "Teléfono chileno inválido"
    )
    private String telefono;

}
