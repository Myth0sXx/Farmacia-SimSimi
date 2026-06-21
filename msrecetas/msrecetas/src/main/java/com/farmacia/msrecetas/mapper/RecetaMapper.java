package com.farmacia.msrecetas.mapper;
import com.farmacia.msrecetas.dto.response.RecetaResponseDTO;
import com.farmacia.msrecetas.model.Receta;
import org.springframework.stereotype.Component;

@Component
public class RecetaMapper {

    public RecetaResponseDTO toDTO(Receta receta) {
        RecetaResponseDTO dto = new RecetaResponseDTO();
        dto.setId(receta.getId());
        dto.setClienteId(receta.getClienteId());
        dto.setDoctorNombre(receta.getDoctorNombre());
        dto.setFecha(receta.getFecha());
        dto.setEstado(receta.getEstado());
        return dto;
    }
}
