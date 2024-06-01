package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.DetalleOrden;
import com.andres.app.ecommerce.ecommerce_app.repositories.DetalleOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleOrdenServiceImpl implements DetalleOrdenService{

    @Autowired
    private DetalleOrdenRepository repository;


    @Override
    public DetalleOrden agregarDetalleOrden(DetalleOrden detalleOrden) {
        return repository.save(detalleOrden);
    }

    @Override
    public List<DetalleOrden> listarDetalleOrden() {
        return (List<DetalleOrden>) repository.findAll();
    }

    @Override
    public Optional<DetalleOrden> buscarDetalleOrdenPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<DetalleOrden> eliminarDetalleOrden(Long id) {
        Optional<DetalleOrden> detalleOrdenOptional = repository.findById(id);
        detalleOrdenOptional.ifPresent(detalleOrden -> repository.deleteById(id));
        return detalleOrdenOptional;
    }

    @Override
    public Optional<DetalleOrden> actualizarDetalleOrden(Long id, DetalleOrden detalleOrden) {
        Optional<DetalleOrden> detalleOrdenOptional = repository.findById(id);
        detalleOrdenOptional.ifPresent(detalleOrdenDb -> {
            detalleOrden.setIdDetalleOrden(id);
            repository.save(detalleOrden);
        });
        return detalleOrdenOptional;
    }
}
