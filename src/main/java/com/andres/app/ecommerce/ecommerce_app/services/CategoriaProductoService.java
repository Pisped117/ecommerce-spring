package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.CategoriaProducto;

import java.util.List;
import java.util.Optional;

public interface CategoriaProductoService {

    CategoriaProducto agregarCategoriaProducto(CategoriaProducto categoriaProducto);

    List<CategoriaProducto> listarCategoriaProducto();

    Optional<CategoriaProducto> buscarCategoriaProductoPorId (Long id);

    Optional<CategoriaProducto> eliminarCategoriaProducto(Long id);

    Optional<CategoriaProducto> actualizarCategoriaProducto(Long id, CategoriaProducto categoriaProducto);
}
