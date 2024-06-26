package com.andres.app.ecommerce.ecommerce_app.services.impl;

import com.andres.app.ecommerce.ecommerce_app.models.DetalleOrden;
import com.andres.app.ecommerce.ecommerce_app.models.Orden;
import com.andres.app.ecommerce.ecommerce_app.repositories.DetalleOrdenRepository;
import com.andres.app.ecommerce.ecommerce_app.services.DetalleOrdenService;
import com.andres.app.ecommerce.ecommerce_app.services.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleOrdenServiceImpl implements DetalleOrdenService {

    @Autowired
    private DetalleOrdenRepository repository;

    @Autowired
    private OrdenService ordenService;


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

    @Override
    public List<Object[]> listar5ProductosMasVendidos() {
        return repository.hayarProductosMasVendidos();
    }
}
