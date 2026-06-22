package com.farmacia.msrecetas.service.intefaces;
import com.farmacia.msrecetas.dto.request.RecetaRequestDTO;
import com.farmacia.msrecetas.dto.response.RecetaResponseDTO;
import com.farmacia.msrecetas.dto.validar.ValidarRecetaDTO;

import java.util.List;


public interface RecetaService {
    RecetaResponseDTO crearReceta(RecetaRequestDTO dto);
    List<RecetaResponseDTO> listarRecetas();
    boolean validarReceta(ValidarRecetaDTO dto);
    void marcarComoUsada(Long recetaId);
}
