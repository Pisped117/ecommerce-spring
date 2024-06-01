package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.TipoDescuento;

import java.util.List;
import java.util.Optional;

public interface TipoDescuentoService {

    TipoDescuento agregarTipoDescuento(TipoDescuento tipoDescuento);

    List<TipoDescuento> listarTipoDescuento();

    Optional<TipoDescuento> buscarTipoDescuentoPorId (Long id);

    Optional<TipoDescuento> eliminarTipoDescuento(Long id);

    Optional<TipoDescuento> actualizarTipoDescuento (Long id, TipoDescuento tipoDescuento);
}
