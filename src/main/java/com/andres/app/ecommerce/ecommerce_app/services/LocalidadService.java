package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.Localidad;

import java.util.List;
import java.util.Optional;

public interface LocalidadService {

    Localidad agregarLocalidad (Localidad localidad);

    List<Localidad> listarLocalidad();

    Optional<Localidad> buscarLocalidadTorId (Long id);

    Optional<Localidad> eliminarLocalidad(Long id);

    Optional<Localidad> actualizarLocalidad(Long id, Localidad localidad);
}
