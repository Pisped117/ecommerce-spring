package com.andres.app.ecommerce.ecommerce_app.services.impl;

import com.andres.app.ecommerce.ecommerce_app.models.Usuario;
import com.andres.app.ecommerce.ecommerce_app.repositories.UsuarioRepository;
import com.andres.app.ecommerce.ecommerce_app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Usuario agregarUsuario(Usuario usuario) {
        boolean validarNombreDeUsuario = repository.existsByNombreUsuario(usuario.getNombreUsuario());
        System.out.println(validarNombreDeUsuario);
        if (validarNombreDeUsuario){
            return null;
        }
        usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
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
            //Se iguala la lista de perfiles a la consulta para evitar actualizacion de permisos
            usuario.setPerfiles(usuarioOptional.get().getPerfiles());
            usuario.setIdUsuario(usuarioOptional.get().getIdUsuario());
            repository.save(usuario);
        });
        return usuarioOptional;
    }

    @Override
    public boolean validarNombreUsuario(String nombreUsuario) {
        return repository.existsByNombreUsuario(nombreUsuario);
    }
}
