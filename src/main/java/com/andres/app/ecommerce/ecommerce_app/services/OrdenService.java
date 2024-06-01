package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.Orden;

import java.util.List;
import java.util.Optional;

public interface OrdenService {

    Orden agregarOrden (Orden orden);

    List<Orden> listarOrden ();

    Optional<Orden> buscarOrdenPorId (Long id);

    Optional<Orden> eliminarOrden (Long id);

    Optional<Orden> actualizarOrden (Long id, Orden orden);
}
