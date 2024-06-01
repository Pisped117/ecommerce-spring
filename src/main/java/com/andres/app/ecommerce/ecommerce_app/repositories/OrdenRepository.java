package com.andres.app.ecommerce.ecommerce_app.repositories;

import com.andres.app.ecommerce.ecommerce_app.models.Orden;
import org.springframework.data.repository.CrudRepository;

public interface OrdenRepository extends CrudRepository<Orden, Long> {
}
