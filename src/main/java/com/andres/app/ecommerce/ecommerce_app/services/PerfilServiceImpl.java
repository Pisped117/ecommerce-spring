package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.Perfil;
import com.andres.app.ecommerce.ecommerce_app.repositories.PefilRepository;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilServiceImpl implements PerfilService{

    @Autowired
    private PefilRepository repository;

    @Transactional
    @Override
    public Perfil agregarPerfil(Perfil perfil) {
        return repository.save(perfil);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Perfil> listarPerfil() {
        return (List<Perfil>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Perfil> buscarPerfilPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Optional<Perfil> eliminarPerfil(Long id) {
        Optional<Perfil> perfilOptional = repository.findById(id);
        perfilOptional.ifPresent(perfil -> repository.deleteById(id));
        return perfilOptional;
    }

    @Transactional
    @Override
    public Optional<Perfil> actualizarPerfil(Long id, Perfil perfil) {
        Optional<Perfil> perfilOptional = repository.findById(id);
        perfilOptional.ifPresent(perfilDb -> {
            perfil.setIdPerfil(id);
            repository.save(perfil);
        });
        return perfilOptional;
    }
}
