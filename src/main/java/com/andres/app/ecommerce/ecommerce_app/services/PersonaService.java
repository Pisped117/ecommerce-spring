package com.andres.app.ecommerce.ecommerce_app.services;


import com.andres.app.ecommerce.ecommerce_app.models.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaService {

    Persona agregarPersona (Persona persona);

    List<Persona> listarPersonas ();

    Optional<Persona> buscarPersonaPorId (Long id);

    Optional <Persona>eliminarPersona (Long id);

    Optional<Persona> actualizarPersona (Long id, Persona persona);
}
