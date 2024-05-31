package com.andres.app.ecommerce.ecommerce_app.repositories;

import com.andres.app.ecommerce.ecommerce_app.models.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PersonaRepository extends CrudRepository<Persona, Long> {

    @Query("select p.numeroDocumento from Persona p where p.numeroDocumento = ?1")
    String validarNumeroDocumento(String numeroDocumento);

}
