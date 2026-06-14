package com.farmacia.msventas.service.impl;

import com.farmacia.msventas.client.DetalleVentaClient;
import com.farmacia.msventas.client.InventarioClient;
import com.farmacia.msventas.client.NotificacionClient;
import com.farmacia.msventas.client.PagoClient;
import com.farmacia.msventas.client.RecetaClient;

import com.farmacia.msventas.dto.DetalleVentaDTO;
import com.farmacia.msventas.dto.NotificacionDTO;
import com.farmacia.msventas.dto.PagoDTO;
import com.farmacia.msventas.dto.RecetaDTO;
import com.farmacia.msventas.dto.StockDTO;
import com.farmacia.msventas.dto.VentaRequestDTO;
import com.farmacia.msventas.dto.VentaResponseDTO;

import com.farmacia.msventas.exception.VentaException;

import com.farmacia.msventas.mapper.VentaMapper;

import com.farmacia.msventas.model.EstadoVenta;
import com.farmacia.msventas.model.MetodoPago;
import com.farmacia.msventas.model.Venta;

import com.farmacia.msventas.repository.VentaRepository;

import com.farmacia.msventas.service.VentaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class VentaServiceImpl
        implements VentaService {

    private final VentaRepository ventaRepository;

    private final InventarioClient inventarioClient;

    private final RecetaClient recetaClient;

    private final DetalleVentaClient detalleVentaClient;

    private final PagoClient pagoClient;

    private final NotificacionClient notificacionClient;

    private final VentaMapper mapper;

    @Override
    public VentaResponseDTO realizarVenta(
            VentaRequestDTO dto
    ) {

        log.info(
                "Iniciando proceso de venta para cliente {}",
                dto.getClienteId()
        );

        Venta venta =
                mapper.toEntity(dto);

        venta = ventaRepository.save(venta);

        log.info(
                "Venta creada en estado PENDIENTE con id {}",
                venta.getId()
        );

        double total = 0;

        for (DetalleVentaDTO detalle
                : dto.getDetalles()) {

            log.info(
                    "Procesando medicamento {}",
                    detalle.getMedicamentoId()
            );

            if (Boolean.TRUE.equals(
                    detalle.getRequiereReceta()
            )) {

                log.info(
                        "Validando receta para medicamento {}",
                        detalle.getMedicamentoId()
                );

                RecetaDTO recetaDTO =
                        new RecetaDTO();

                recetaDTO.setClienteId(
                        dto.getClienteId()
                );

                Boolean valida =
                        recetaClient.validarReceta(
                                recetaDTO
                        );

                if (!Boolean.TRUE.equals(valida)) {

                    log.warn(
                            "Cliente {} no posee receta válida",
                            dto.getClienteId()
                    );

                    throw new VentaException(
                            "El cliente no posee una receta válida"
                    );
                }

                log.info(
                        "Receta validada correctamente"
                );
            }

            StockDTO stockDTO =
                    new StockDTO();

            stockDTO.setMedicamentoId(
                    detalle.getMedicamentoId()
            );

            stockDTO.setCantidad(
                    detalle.getCantidad()
            );

            log.info(
                    "Descontando stock medicamento {}",
                    detalle.getMedicamentoId()
            );

            inventarioClient
                    .descontarStock(stockDTO);

            total += detalle.getCantidad()
                    * detalle.getPrecioUnitario();
        }

        log.info(
                "Total calculado de venta: {}",
                total
        );

        Venta finalVenta = venta;

        dto.getDetalles()
                .forEach(d ->
                        d.setVentaId(
                                finalVenta.getId()
                        )
                );

        log.info(
                "Guardando detalles de venta {}",
                venta.getId()
        );

        detalleVentaClient.guardarDetalles(
                dto.getDetalles()
        );

        PagoDTO pago =
                new PagoDTO();

        pago.setVentaId(venta.getId());

        pago.setMonto(total);

        pago.setMetodo(
                MetodoPago.EFECTIVO
        );

        log.info(
                "Registrando pago para venta {}",
                venta.getId()
        );

        pagoClient.registrarPago(pago);

        venta.setTotal(total);

        venta.setEstado(
                EstadoVenta.PAGADA
        );

        Venta guardada =
                ventaRepository.save(venta);

        log.info(
                "Venta {} actualizada a estado PAGADA",
                guardada.getId()
        );

        NotificacionDTO notif =
                new NotificacionDTO();

        notif.setMensaje(
                "Venta realizada correctamente. ID: "
                        + guardada.getId()
        );

        log.info(
                "Enviando notificación de venta {}",
                guardada.getId()
        );

        notificacionClient
                .enviarNotificacion(notif);

        log.info(
                "Proceso de venta finalizado correctamente con id {}",
                guardada.getId()
        );

        return mapper.toDTO(guardada);
    }
}