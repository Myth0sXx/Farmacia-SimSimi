package com.farmacia.msusuario.service.interfaces;
import com.farmacia.msusuario.dto.request.UsuarioRequestDTO;
import com.farmacia.msusuario.dto.response.UsuarioResponseDTO;
import java.util.List;

public interface UsuarioService {
    UsuarioResponseDTO crearUsuario(UsuarioRequestDTO dto);
    UsuarioResponseDTO obtenerUsuarioPorId(Long id);
    List<UsuarioResponseDTO> listarUsuarios();
    UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO dto);
    void eliminarUsuario(Long id);
}

