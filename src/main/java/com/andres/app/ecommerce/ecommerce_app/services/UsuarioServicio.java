package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioServicio {

    Usuario agregarUsuario (Usuario usuario);

    List<Usuario> listarUsuarios ();

    Optional<Usuario> buscarUsuaropPorId (Long id);

    Optional<Usuario> eliminarUsuario (Long id);

    Optional<Usuario> actualizarUsuario (Long id, Usuario usuario);
}
