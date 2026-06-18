package com.farmacia.msreportes.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.Hidden;

@FeignClient(
        name = "msventas",
        url = "http://localhost:8082"
)
@Hidden
public interface VentaClient {

    @GetMapping("/api/ventas")
    String obtenerVentas();
}

