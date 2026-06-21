package com.farmacia.msusuario.controller;
import com.farmacia.msusuario.dto.request.UsuarioRequestDTO;
import com.farmacia.msusuario.dto.response.UsuarioResponseDTO;
import com.farmacia.msusuario.service.interfaces.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
// BUENA PRÁCTICA: Versionar la API (v1) ayuda a mantener control y orden en el Gateway
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService service;

    @Operation(summary = "Crear un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    // BUENA PRÁCTICA: El método se llama 'save' o 'create', evitando redundancias como 'crearUsuario'
    public ResponseEntity<UsuarioResponseDTO> create(@Valid @RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.crearUsuario(dto));
    }

    @Operation(summary = "Obtener un usuario por su ID")
    @GetMapping("/{id}")
    // BUENA PRÁCTICA: Nombre claro alineado al recurso individual
    public ResponseEntity<UsuarioResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerUsuarioPorId(id));
    }

    @Operation(summary = "Listar todos los usuarios")
    @GetMapping
    // BUENA PRÁCTICA: Nombre que representa la acción sobre la colección completa
    public ResponseEntity<List<UsuarioResponseDTO>> findAll() {
        return ResponseEntity.ok(service.listarUsuarios());
    }

    @Operation(summary = "Actualizar un usuario existente")
    @PutMapping("/{id}")
    // BUENA PRÁCTICA: Nombre corto, la acción PUT ya define la actualización sobre el ID
    public ResponseEntity<UsuarioResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.ok(service.actualizarUsuario(id, dto));
    }

    @Operation(summary = "Eliminar un usuario por su ID")
    @DeleteMapping("/{id}")
    // BUENA PRÁCTICA: Nombre alineado a la eliminación física/lógica del recurso
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}

