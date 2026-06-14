package com.farmacia.msrecetas.service.impl;
import com.farmacia.msrecetas.dto.RecetaRequestDTO;
import com.farmacia.msrecetas.dto.RecetaResponseDTO;
import com.farmacia.msrecetas.dto.ValidarRecetaDTO;
import com.farmacia.msrecetas.exception.ResourceNotFoundException;
import com.farmacia.msrecetas.mapper.RecetaMapper;
import com.farmacia.msrecetas.model.EstadoReceta;
import com.farmacia.msrecetas.model.Receta;
import com.farmacia.msrecetas.repository.RecetaRepository;
import com.farmacia.msrecetas.service.RecetaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RecetaServiceImpl implements RecetaService {

    private final RecetaRepository repository;
    private final RecetaMapper mapper;

    @Override
    public RecetaResponseDTO crearReceta(RecetaRequestDTO dto) {

        log.info("Creando receta para cliente {}", dto.getClienteId());

        Receta receta = new Receta();
        receta.setClienteId(dto.getClienteId());
        receta.setDoctorNombre(dto.getDoctorNombre());

        Receta guardada = repository.save(receta);

        log.info("Receta creada con ID {}", guardada.getId());

        return mapper.toDTO(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecetaResponseDTO> listarRecetas() {

        log.info("Listando todas las recetas");

        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public boolean validarReceta(ValidarRecetaDTO dto) {

        log.info("Validando receta para cliente {}", dto.getClienteId());

        boolean existe = !repository
                .findByClienteIdAndEstado(dto.getClienteId(), EstadoReceta.ACTIVA)
                .isEmpty();

        log.info("Resultado validación: {}", existe);

        return existe;
    }

    @Override
    public void marcarComoUsada(Long recetaId) {

        log.info("Marcando receta como usada ID {}", recetaId);

        Receta receta = repository.findById(recetaId)
                .orElseThrow(() -> {
                    log.error("Receta no encontrada ID {}", recetaId);
                    return new ResourceNotFoundException("Receta no encontrada");
                });

        receta.setEstado(EstadoReceta.USADA);
        repository.save(receta);

        log.info("Receta marcada como USADA");
    }
}