package com.andres.app.ecommerce.ecommerce_app.repositories;

import com.andres.app.ecommerce.ecommerce_app.models.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    @Query("select u.nombreUsuario from Usuario u where u.nombreUsuario = ?1")
    String validarNombreDeUsuario(String nombreUsuario);
}
