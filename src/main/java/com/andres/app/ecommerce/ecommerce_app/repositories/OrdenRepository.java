package com.andres.app.ecommerce.ecommerce_app.repositories;

import com.andres.app.ecommerce.ecommerce_app.models.Cliente;
import com.andres.app.ecommerce.ecommerce_app.models.Orden;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface OrdenRepository extends CrudRepository<Orden, Long> {

    @Query("select o.cliente, count(o) from Orden o group by o.cliente.idCliente having count(o) > 5")
    List<Cliente> buscarClientesFrecuentes ();
}
