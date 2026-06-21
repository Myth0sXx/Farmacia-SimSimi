package com.farmacia.mspagos.service.interfaces;
import com.farmacia.mspagos.dto.request.PagoRequestDTO;
import com.farmacia.mspagos.dto.response.PagoResponseDTO;

import java.util.List;
public interface PagoService {
    PagoResponseDTO procesarPago(PagoRequestDTO dto);
    List<PagoResponseDTO> listarPagos();
    List<PagoResponseDTO> obtenerPorVenta(Long ventaId);
}