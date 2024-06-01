package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.Direccion;

import java.util.List;
import java.util.Optional;


public interface DireccionService {

    Direccion agregarDireccion (Direccion direccion);

    List<Direccion> listarDireccion ();

    Optional<Direccion> buscarDireccionPorId (Long id);

    Optional<Direccion> eliminarDireccion(Long id);

    Optional<Direccion> actualizarDireccion (Long id, Direccion direccion);
}
