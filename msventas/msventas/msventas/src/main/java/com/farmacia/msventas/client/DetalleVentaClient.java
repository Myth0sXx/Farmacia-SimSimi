package com.farmacia.msventas.client;
import com.farmacia.msventas.dto.DetalleVentaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient(
        name = "msdetalleventa",
        url = "http://localhost:8083"
)
public interface DetalleVentaClient {

    @PostMapping("/api/detalle-venta/lote")
    Void guardarDetalles(
            @RequestBody List<DetalleVentaDTO> detalles
    );
}

