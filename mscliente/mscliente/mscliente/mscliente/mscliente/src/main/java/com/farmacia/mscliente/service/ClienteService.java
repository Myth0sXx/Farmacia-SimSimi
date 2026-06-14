package com.farmacia.mscliente.service;
import com.farmacia.mscliente.dto.*;

import java.util.List;
public interface ClienteService {
    ClienteResponseDTO crear(ClienteRequestDTO dto);
    ClienteResponseDTO obtenerPorId(Long id);
    List<ClienteResponseDTO> listar();
    ClienteResponseDTO actualizar(Long id, ClienteRequestDTO dto);
    void eliminar(Long id);
}
