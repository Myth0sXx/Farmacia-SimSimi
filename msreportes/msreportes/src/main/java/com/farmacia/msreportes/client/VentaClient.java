package com.farmacia.msreportes.client;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "ms-ventas",
        fallback = VentaClientFallback.class
)
public interface VentaClient {

    @GetMapping("/api/ventas")
    String obtenerVentas();
}
