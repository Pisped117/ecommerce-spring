package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.DetalleOrden;

import java.util.List;
import java.util.Optional;

public interface DetalleOrdenService {

    DetalleOrden agregarDetalleOrden (DetalleOrden detalleOrden);

    List<DetalleOrden> listarDetalleOrden();

    Optional<DetalleOrden> buscarDetalleOrdenPorId(Long id);

    Optional<DetalleOrden> eliminarDetalleOrden(Long id);

    Optional<DetalleOrden> actualizarDetalleOrden (Long id, DetalleOrden detalleOrden);
}
