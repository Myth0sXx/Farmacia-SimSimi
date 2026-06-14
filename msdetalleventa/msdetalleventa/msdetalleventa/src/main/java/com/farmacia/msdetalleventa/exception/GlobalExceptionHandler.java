package com.farmacia.msdetalleventa.exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            ResourceNotFoundException.class
    )
    public ResponseEntity<Map<String, String>>
    manejarNoEncontrado(
            ResourceNotFoundException ex
    ) {

        log.warn(
                "Recurso no encontrado: {}",
                ex.getMessage()
        );

        Map<String, String> error =
                new HashMap<>();

        error.put(
                "mensaje",
                ex.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    public ResponseEntity<Map<String, String>>
    manejarValidaciones(
            MethodArgumentNotValidException ex
    ) {

        log.warn(
                "Error de validación en request"
        );

        Map<String, String> errores =
                new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errores.put(
                                error.getField(),
                                error.getDefaultMessage()
                        ));

        return ResponseEntity
                .badRequest()
                .body(errores);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>>
    manejarGeneral(
            Exception ex
    ) {

        log.error(
                "Error interno del servidor",
                ex
        );

        Map<String, String> error =
                new HashMap<>();

        error.put(
                "mensaje",
                "Error interno del servidor"
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}

