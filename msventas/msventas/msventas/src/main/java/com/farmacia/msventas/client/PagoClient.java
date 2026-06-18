package com.farmacia.msventas.client;

import com.farmacia.msventas.dto.PagoDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Hidden;

@FeignClient(
        name = "mspagos",
        url = "http://localhost:8084"
)
@Hidden
public interface PagoClient {

    @PostMapping("/api/pagos")
    Void registrarPago(
            @RequestBody PagoDTO dto
    );
}

