package com.farmacia.msventas.dto;
import com.farmacia.msventas.model.MetodoPago;
import lombok.Data;

@Data
public class PagoDTO {
    private Long ventaId;
    private Double monto;
    private MetodoPago metodo;
}