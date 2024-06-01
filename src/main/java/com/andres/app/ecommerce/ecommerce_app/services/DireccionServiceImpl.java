package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.Direccion;
import com.andres.app.ecommerce.ecommerce_app.repositories.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionServiceImpl implements DireccionService{

    @Autowired
    private DireccionRepository repository;

    @Override
    public Direccion agregarDireccion(Direccion direccion) {
        return repository.save(direccion);
    }

    @Override
    public List<Direccion> listarDireccion() {
        return  (List<Direccion>) repository.findAll();
    }

    @Override
    public Optional<Direccion> buscarDireccionPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Direccion> eliminarDireccion(Long id) {
        Optional<Direccion> direccionOptional = repository.findById(id);
        direccionOptional.ifPresent(direccion -> repository.deleteById(id));
        return direccionOptional;
    }

    @Override
    public Optional<Direccion> actualizarDireccion(Long id, Direccion direccion) {
        Optional<Direccion> direccionOptional = repository.findById(id);
        direccionOptional.ifPresent(direccionDb -> {
            direccion.setIdDireccion(id);
            repository.save(direccion);
        });
        return direccionOptional;
    }
}
