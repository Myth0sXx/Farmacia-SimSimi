package com.farmacia.msreportes.client;
import org.springframework.stereotype.Component;

@Component
public class VentaClientFallback implements VentaClient {

    @Override
    public String obtenerVentas() {
        return "[]"; // fallback seguro cuando ms-ventas falla
    }
}
