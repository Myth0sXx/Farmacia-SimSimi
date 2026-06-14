package com.farmacia.msinventario.service;

import com.farmacia.msinventario.dto.ActualizarStockDTO;
import com.farmacia.msinventario.dto.InventarioRequestDTO;
import com.farmacia.msinventario.dto.InventarioResponseDTO;

import java.util.List;

public interface InventarioService {
    InventarioResponseDTO crearOActualizar(
            InventarioRequestDTO dto
    );
    InventarioResponseDTO obtenerPorMedicamentoId(
            Long medicamentoId
    );

    List<InventarioResponseDTO> listarInventario();
    void descontarStock(ActualizarStockDTO dto);
}

