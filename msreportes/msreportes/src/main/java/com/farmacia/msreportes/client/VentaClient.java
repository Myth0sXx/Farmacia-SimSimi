package com.farmacia.msreportes.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "msventas",
        url = "http://localhost:8082"
)
public interface VentaClient {

    @GetMapping("/api/ventas")
    String obtenerVentas();
}

