package com.farmacia.mspagos.exception;

public class PagoException extends RuntimeException {
    public PagoException(String mensaje) {
        super(mensaje);
    }
}

