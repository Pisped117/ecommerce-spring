package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.ClienteFrecuente;

import java.util.List;
import java.util.Optional;

public interface ClienteFrecuenteService {

    ClienteFrecuente agregarClienteFrecuente (ClienteFrecuente clienteFrecuente);

    List<ClienteFrecuente> listarClienteFrecuente();

    Optional<ClienteFrecuente> buscarClienteFrecuente(Long id);

    Optional<ClienteFrecuente> eliminarClienteFrecuente(Long id);

    Optional<ClienteFrecuente> actualizarClienteFrecuente(Long id, ClienteFrecuente clienteFrecuente);
}
