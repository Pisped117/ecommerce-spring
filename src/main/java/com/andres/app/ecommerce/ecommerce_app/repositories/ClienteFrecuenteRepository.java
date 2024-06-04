package com.andres.app.ecommerce.ecommerce_app.repositories;

import com.andres.app.ecommerce.ecommerce_app.models.ClienteFrecuente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClienteFrecuenteRepository extends CrudRepository<ClienteFrecuente, Long> {

    //select cf.cliente.idCliente from ClienteFrecuente cf where cf.cliente.idCliente = ?1
    @Query("select cf.cliente.idCliente from ClienteFrecuente cf where cf.cliente.idCliente = ?1")
    Long buscarClienteFrecuenteRegistrado(Long id);
}
