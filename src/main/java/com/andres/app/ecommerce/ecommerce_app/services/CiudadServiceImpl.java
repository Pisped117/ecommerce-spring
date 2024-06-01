package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.Ciudad;
import com.andres.app.ecommerce.ecommerce_app.repositories.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadServiceImpl implements CiudadService{

    @Autowired
    private CiudadRepository repository;

    @Transactional
    @Override
    public Ciudad agregarCiudad(Ciudad ciudad) {
        return repository.save(ciudad);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Ciudad> listarCiudad() {
        return (List<Ciudad>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Ciudad> buscarCiudadTorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Optional<Ciudad> eliminarCiudad(Long id) {
        Optional<Ciudad> ciudadOptional = repository.findById(id);
        ciudadOptional.ifPresent(ciudad -> repository.deleteById(id));
        return ciudadOptional;
    }

    @Transactional
    @Override
    public Optional<Ciudad> actualizarCiudad(Long id, Ciudad ciudad) {
        Optional<Ciudad> ciudadOptional = repository.findById(id);
        ciudadOptional.ifPresent(ciudadDb -> {
            ciudad.setIdCiudad(id);
            repository.save(ciudad);
        });
        return ciudadOptional;
    }
}
