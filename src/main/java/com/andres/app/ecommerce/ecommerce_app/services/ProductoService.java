package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoService {

    Producto agregarProducto (Producto producto);

    List<Producto> listarProducto ();

    Optional<Producto> buscarProductoPorId (Long id);

    Optional<Producto> eliminarProducto (Long id);

    Optional<Producto> actualizarProdcuto (Long id, Producto producto);

}
