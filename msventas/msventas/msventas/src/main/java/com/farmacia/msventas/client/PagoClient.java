package com.farmacia.msventas.client;

import com.farmacia.msventas.dto.PagoDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "mspagos",
        url = "http://localhost:8084"
)
public interface PagoClient {

    @PostMapping("/api/pagos")
    Void registrarPago(
            @RequestBody PagoDTO dto
    );
}

