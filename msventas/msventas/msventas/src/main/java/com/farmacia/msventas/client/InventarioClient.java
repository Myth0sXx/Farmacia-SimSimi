package com.farmacia.msventas.client;
import com.farmacia.msventas.dto.StockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "msinventario",
        url = "http://localhost:8086"
)
public interface InventarioClient {

    @PostMapping("/api/inventario/descontar")
    Void descontarStock(
            @RequestBody StockDTO dto
    );
}
