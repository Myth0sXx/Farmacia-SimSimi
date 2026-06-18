package com.farmacia.msventas.client;
import com.farmacia.msventas.dto.StockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Hidden;

@FeignClient(
        name = "msinventario",
        url = "http://localhost:8086"
)
@Hidden
public interface InventarioClient {

    @PostMapping("/api/inventario/descontar")
    void descontarStock(
            @RequestBody StockDTO dto
    );
}
