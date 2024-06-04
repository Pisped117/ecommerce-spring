package com.andres.app.ecommerce.ecommerce_app.repositories;

import com.andres.app.ecommerce.ecommerce_app.models.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

    @Query("select p from Producto p where p.estado = ?1")
    List<Producto> productosActivos(boolean estado);
}
