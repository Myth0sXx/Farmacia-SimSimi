package com.farmacia.msusuario.service;
import com.farmacia.msusuario.dto.UsuarioRequestDTO;
import com.farmacia.msusuario.dto.UsuarioResponseDTO;
import java.util.List;

public interface UsuarioService {
    UsuarioResponseDTO crearUsuario(UsuarioRequestDTO dto);
    UsuarioResponseDTO obtenerUsuarioPorId(Long id);
    List<UsuarioResponseDTO> listarUsuarios();
    UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO dto);
    void eliminarUsuario(Long id);
}
