package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.CategoriaProducto;
import com.andres.app.ecommerce.ecommerce_app.repositories.CategoriaProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaProductoServiceImpl implements CategoriaProductoService{

    @Autowired
    private CategoriaProductoRepository repository;

    @Transactional
    @Override
    public CategoriaProducto agregarCategoriaProducto(CategoriaProducto categoriaProducto) {
        return repository.save(categoriaProducto);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoriaProducto> listarCategoriaProducto() {
        return (List<CategoriaProducto>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<CategoriaProducto> buscarCategoriaProductoPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Optional<CategoriaProducto> eliminarCategoriaProducto(Long id) {
        Optional<CategoriaProducto> categoriaProductoOptional = repository.findById(id);
        categoriaProductoOptional.ifPresent(categoriaProducto -> repository.deleteById(id));
        return categoriaProductoOptional;
    }

    @Transactional
    @Override
    public Optional<CategoriaProducto> actualizarCategoriaProducto(Long id, CategoriaProducto categoriaProducto) {
        Optional<CategoriaProducto> categoriaProductoOptional = repository.findById(id);
        categoriaProductoOptional.ifPresent(categoriaProductoDb -> {
            categoriaProducto.setIdCategoriaProducto(id);
            repository.save(categoriaProducto);
        });
        return categoriaProductoOptional;
    }
}
