package com.farmacia.msrecetas.service;
import com.farmacia.msrecetas.dto.*;
import java.util.List;

public interface RecetaService {
    RecetaResponseDTO crearReceta(RecetaRequestDTO dto);
    List<RecetaResponseDTO> listarRecetas();
    boolean validarReceta(ValidarRecetaDTO dto);
    void marcarComoUsada(Long recetaId);
}