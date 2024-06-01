package com.andres.app.ecommerce.ecommerce_app.services;
import com.andres.app.ecommerce.ecommerce_app.models.Perfil;

import java.util.List;
import java.util.Optional;

public interface PerfilService {

    Perfil agregarPerfil (Perfil perfil);

    List<Perfil> listarPerfil();

    Optional<Perfil> buscarPerfilPorId (Long id);

    Optional<Perfil> eliminarPerfil(Long id);

    Optional<Perfil> actualizarPerfil (Long id, Perfil perfil);
}
