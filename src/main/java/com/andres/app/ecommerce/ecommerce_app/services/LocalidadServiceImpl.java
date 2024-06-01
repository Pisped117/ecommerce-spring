package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.Localidad;
import com.andres.app.ecommerce.ecommerce_app.repositories.LocalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LocalidadServiceImpl implements LocalidadService{

    @Autowired
    private LocalidadRepository repository;

    @Transactional
    @Override
    public Localidad agregarLocalidad(Localidad localidad) {
        return repository.save(localidad);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Localidad> listarLocalidad() {
        return (List<Localidad>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Localidad> buscarLocalidadTorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Optional<Localidad> eliminarLocalidad(Long id) {
        Optional<Localidad> localidadOptional = repository.findById(id);
        localidadOptional.ifPresent(localidad -> repository.deleteById(id));
        return localidadOptional;
    }

    @Transactional
    @Override
    public Optional<Localidad> actualizarLocalidad(Long id, Localidad localidad) {
        Optional<Localidad> localidadOptional = repository.findById(id);
        localidadOptional.ifPresent(localidadDb -> {
            localidad.setIdLocalidad(id);
            repository.save(localidad);
        });
        return localidadOptional;
    }
}
