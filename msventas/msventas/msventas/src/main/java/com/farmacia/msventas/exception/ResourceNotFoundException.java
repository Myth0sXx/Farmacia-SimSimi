package com.farmacia.msventas.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(
            String mensaje
    ) {
        super(mensaje);
    }
}
