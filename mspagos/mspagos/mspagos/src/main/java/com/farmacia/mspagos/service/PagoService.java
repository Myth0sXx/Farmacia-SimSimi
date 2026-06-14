package com.farmacia.mspagos.service;
import com.farmacia.mspagos.dto.PagoRequestDTO;
import com.farmacia.mspagos.dto.PagoResponseDTO;

import java.util.List;
public interface PagoService {
    PagoResponseDTO procesarPago(PagoRequestDTO dto);
    List<PagoResponseDTO> listarPagos();
    List<PagoResponseDTO> obtenerPorVenta(Long ventaId);
}
