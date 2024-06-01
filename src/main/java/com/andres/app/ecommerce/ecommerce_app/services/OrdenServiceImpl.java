package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.Orden;
import com.andres.app.ecommerce.ecommerce_app.repositories.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrdenServiceImpl implements OrdenService{

    @Autowired
    OrdenRepository repository;

    @Override
    public Orden agregarOrden(Orden orden) {
        orden.setFechaVenta(new Date());
        return repository.save(orden);
    }

    @Override
    public List<Orden> listarOrden() {
        return (List<Orden>) repository.findAll();
    }

    @Override
    public Optional<Orden> buscarOrdenPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Orden> eliminarOrden(Long id) {
        Optional<Orden> optionalOrden = repository.findById(id);
        optionalOrden.ifPresent(orden -> repository.deleteById(id));
        return optionalOrden;
    }

    @Override
    public Optional<Orden> actualizarOrden(Long id, Orden orden) {
        Optional<Orden> ordenOptional = repository.findById(id);
        ordenOptional.ifPresent(ordenDb -> {
            orden.setIdOrden(id);
            orden.setFechaVenta(ordenOptional.get().getFechaVenta());
            repository.save(orden);
        });
        return ordenOptional;
    }
}
