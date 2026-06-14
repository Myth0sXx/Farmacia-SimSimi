package com.farmacia.mspagos.exception;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
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
