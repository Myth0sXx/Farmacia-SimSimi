package com.farmacia.msmedicamentos.service;
import com.farmacia.msmedicamentos.dto.*;

import java.util.List;
public interface MedicamentoService {
    MedicamentoResponseDTO crear(MedicamentoRequestDTO dto);

    List<MedicamentoResponseDTO> listar();

    MedicamentoResponseDTO obtenerPorId(Long id);

    List<MedicamentoResponseDTO> buscarPorNombre(String nombre);

    MedicamentoResponseDTO actualizar(
            Long id,
            MedicamentoRequestDTO dto
    );

    void eliminar(Long id);
}
