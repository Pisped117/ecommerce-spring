package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.Producto;
import com.andres.app.ecommerce.ecommerce_app.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository repository;

    @Transactional
    @Override
    public Producto agregarProducto(Producto producto) {
        return repository.save(producto);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Producto> listarProducto() {
        return (List<Producto>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Producto> buscarProductoPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Optional<Producto> eliminarProducto(Long id) {
        Optional<Producto> productoOptional = repository.findById(id);
        productoOptional.ifPresent(producto -> repository.deleteById(id));
        return productoOptional;
    }

    @Transactional
    @Override
    public Optional<Producto> actualizarProdcuto(Long id, Producto producto) {
        Optional<Producto> productoOptional = repository.findById(id);
        productoOptional.ifPresent(productoBd -> {
            producto.setIdProducto(productoOptional.get().getIdProducto());
            repository.save(producto);
        });
        return productoOptional;
    }
}
