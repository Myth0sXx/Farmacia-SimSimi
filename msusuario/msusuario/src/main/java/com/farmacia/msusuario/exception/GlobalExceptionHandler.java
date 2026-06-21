package com.farmacia.msusuario.exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> notFound(
            ResourceNotFoundException ex) {

        log.warn("No encontrado: {}", ex.getMessage());

        Map<String, Object> response = new HashMap<>();
        response.put("error", "No encontrado");
        response.put("mensaje", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Map<String, Object>> duplicate(
            DuplicateEmailException ex) {

        log.warn("Email duplicado: {}", ex.getMessage());

        Map<String, Object> response = new HashMap<>();
        response.put("error", "Conflicto");
        response.put("mensaje", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> validation(
            MethodArgumentNotValidException ex) {

        Map<String, Object> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        return ResponseEntity
                .badRequest()
                .body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> general(
            Exception ex) {

        log.error("Error interno del servidor", ex);

        Map<String, Object> response = new HashMap<>();
        response.put("error", "Error interno del servidor");

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}

