package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.Roles;
import com.andres.app.ecommerce.ecommerce_app.repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RolesServiceImpl implements RolesService{

    @Autowired
    private RolesRepository repository;

    @Transactional
    @Override
    public Roles agregarRoles(Roles roles) {
        return repository.save(roles);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Roles> listarRoles() {
        return (List<Roles>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Roles> buscarRolesPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Optional<Roles> eliminarRoles(Long id) {
        Optional<Roles> rolesOptional = repository.findById(id);
        rolesOptional.ifPresent(roles -> repository.deleteById(id));
        return rolesOptional;
    }

    @Transactional
    @Override
    public Optional<Roles> actualizarRoles(Long id, Roles roles) {
        Optional<Roles> rolesOptional = repository.findById(id);
        rolesOptional.ifPresent(rolesDb -> {
            roles.setIdRol(id);
            repository.save(roles);
        });
        return rolesOptional;
    }
}
