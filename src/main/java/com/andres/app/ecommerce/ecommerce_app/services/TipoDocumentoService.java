package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.TipoDocumento;

import java.util.List;
import java.util.Optional;

public interface TipoDocumentoService {

    TipoDocumento agregarTipoDocumento (TipoDocumento tipoDocumento);

    List<TipoDocumento> listarTipoDocumento ();

    Optional<TipoDocumento> buscarTipoDocumentoPorId (Long id);

    Optional<TipoDocumento> eliminarTipoDocumento (Long id);

    Optional<TipoDocumento> actualizarTipoDocumento (Long id, TipoDocumento tipoDocumento);
}
