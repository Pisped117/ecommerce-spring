package com.andres.app.ecommerce.ecommerce_app.repositories;

import com.andres.app.ecommerce.ecommerce_app.models.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto, Long> {
}
