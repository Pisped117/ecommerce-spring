package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.Descuentos;

import java.util.List;
import java.util.Optional;

public interface DescuentosService {

    Descuentos agregarDescuento (Descuentos descuento);

    List<Descuentos> listarDescuentos ();

    Optional<Descuentos> buscarDescuentoPorId (Long id);

    Optional<Descuentos> eliminarDescuento (Long id);

    Optional<Descuentos> actualizarDescuento (Long id, Descuentos descuento);

}
