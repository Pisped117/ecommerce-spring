package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.Roles;

import java.util.List;
import java.util.Optional;

public interface RolesService {

    Roles agregarRoles (Roles roles);

    List<Roles> listarRoles ();

    Optional<Roles> buscarRolesPorId (Long id);

    Optional<Roles> eliminarRoles(Long id);

    Optional<Roles> actualizarRoles (Long id, Roles roles);
}
