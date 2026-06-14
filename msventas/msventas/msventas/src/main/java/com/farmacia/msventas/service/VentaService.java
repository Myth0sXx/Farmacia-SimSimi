package com.farmacia.msventas.service;
import com.farmacia.msventas.dto.*;

public interface VentaService {
    VentaResponseDTO realizarVenta(
            VentaRequestDTO dto
    );
}
