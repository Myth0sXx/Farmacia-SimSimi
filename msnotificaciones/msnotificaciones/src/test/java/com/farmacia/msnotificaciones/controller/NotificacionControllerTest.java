package com.farmacia.msnotificaciones.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.farmacia.msnotificaciones.dto.request.NotificacionRequestDTO;
import com.farmacia.msnotificaciones.dto.response.NotificacionResponseDTO;
import com.farmacia.msnotificaciones.service.interfaces.NotificacionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NotificacionController.class)

class NotificacionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private NotificacionService service;
    private NotificacionResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        responseDTO = new NotificacionResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setMensaje("Alerta de Venta Registrada");
        responseDTO.setFecha(LocalDateTime.now());
    }

    @Test
    @DisplayName("POST /api/notificaciones - Debería crear una notificación (201 Created)")
    void enviarNotificacionControllerExitoso() throws Exception {
        NotificacionRequestDTO requestDTO = new NotificacionRequestDTO();
        requestDTO.setMensaje("Alerta de Venta Registrada");

        when(service.enviarNotificacion(any(NotificacionRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/notificaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.mensaje").value("Alerta de Venta Registrada"))
                .andExpect(jsonPath("$.fecha").exists());
    }

    @Test
    @DisplayName("POST /api/notificaciones - Debería lanzar 400 Bad Request cuando el mensaje es inválido")
    void enviarNotificacionControllerErrorValidacion() throws Exception {
        NotificacionRequestDTO requestInvalidDTO = new NotificacionRequestDTO();
        requestInvalidDTO.setMensaje(""); // Error: @NotBlank

        mockMvc.perform(post("/api/notificaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestInvalidDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").value("El mensaje es obligatorio"));
    }

    @Test
    @DisplayName("GET /api/notificaciones - Debería listar todas las notificaciones (200 OK)")
    void listarNotificacionesControllerExitoso() throws Exception {
        when(service.listar()).thenReturn(List.of(responseDTO));

        mockMvc.perform(get("/api/notificaciones")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].mensaje").value("Alerta de Venta Registrada"));
    }

    @Test
    @DisplayName("GET /api/notificaciones - Debe retornar 500 cuando ocurre un error interno")
    void listarNotificacionesControllerErrorInterno() throws Exception {

        when(service.listar())
                .thenThrow(new RuntimeException("Error interno"));

        mockMvc.perform(get("/api/notificaciones")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.mensaje")
                        .value("Error interno del servidor"));
    }

}
