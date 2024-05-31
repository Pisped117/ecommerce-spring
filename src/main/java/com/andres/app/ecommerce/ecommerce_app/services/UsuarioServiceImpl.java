package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.Usuario;
import com.andres.app.ecommerce.ecommerce_app.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioServicio{

    @Autowired
    UsuarioRepository repository;

    @Transactional
    @Override
    public Usuario agregarUsuario(Usuario usuario) {
        String nombreUsuario = repository.validarNombreDeUsuario(usuario.getNombreUsuario());
        if (nombreUsuario != null){
            return null;
        }
        return repository.save(usuario);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> listarUsuarios() {
        return (List<Usuario>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Usuario> buscarUsuaropPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Optional<Usuario> eliminarUsuario(Long id) {
        Optional<Usuario> usuarioOptional = repository.findById(id);
        usuarioOptional.ifPresent(usuarioBd -> repository.deleteById(id));
        return usuarioOptional;
    }

    @Transactional
    @Override
    public Optional<Usuario> actualizarUsuario(Long id, Usuario usuario) {
        Optional<Usuario> usuarioOptional = repository.findById(id);
        usuarioOptional.ifPresent(usuarioDb -> {
            usuario.setIdUsuario(usuarioOptional.get().getIdUsuario());
            repository.save(usuario);
        });
        return usuarioOptional;
    }
}
