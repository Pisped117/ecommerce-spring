package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.Ciudad;
import com.andres.app.ecommerce.ecommerce_app.models.TipoDescuento;

import java.util.List;
import java.util.Optional;

public interface CiudadService {

    Ciudad agregarCiudad (Ciudad ciudad);

    List<Ciudad> listarCiudad();

    Optional<Ciudad> buscarCiudadTorId (Long id);

    Optional<Ciudad> eliminarCiudad(Long id);

    Optional<Ciudad> actualizarCiudad (Long id, Ciudad ciudad);
}
