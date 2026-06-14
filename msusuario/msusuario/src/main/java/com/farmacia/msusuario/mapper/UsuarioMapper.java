package com.farmacia.msusuario.mapper;
import com.farmacia.msusuario.dto.UsuarioResponseDTO;
import com.farmacia.msusuario.model.Usuario;
import com.farmacia.msusuario.dto.UsuarioRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setRol(dto.getRol());
        return usuario;
    }

    public UsuarioResponseDTO toDTO(Usuario usuario) {
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .rol(usuario.getRol())
                .build();
    }
}