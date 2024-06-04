package com.andres.app.ecommerce.ecommerce_app.repositories;

import com.andres.app.ecommerce.ecommerce_app.models.DetalleOrden;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DetalleOrdenRepository extends CrudRepository<DetalleOrden, Long> {

    @Query("SELECT p.idProducto, p.nombre, p.precio, p.stock, " +
            "SUM(d.cantidad) AS totalVendido " +
            "FROM DetalleOrden d JOIN d.producto p " +
            "GROUP BY p.idProducto, p.nombre, p.precio, p.stock " +
            "ORDER BY totalVendido DESC")
    List<Object[]> hayarProductosMasVendidos();
}
