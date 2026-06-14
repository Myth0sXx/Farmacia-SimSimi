package com.farmacia.msventas.client;
import com.farmacia.msventas.dto.RecetaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "msrecetas",
        url = "http://localhost:8085"
)
public interface RecetaClient {

    @PostMapping("/api/recetas/validar")
    Boolean validarReceta(
            @RequestBody RecetaDTO dto
    );
}

