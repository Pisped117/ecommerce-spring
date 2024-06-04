package com.andres.app.ecommerce.ecommerce_app.services.impl;

import com.andres.app.ecommerce.ecommerce_app.models.Cliente;
import com.andres.app.ecommerce.ecommerce_app.models.Usuario;
import com.andres.app.ecommerce.ecommerce_app.repositories.ClienteRepository;
import com.andres.app.ecommerce.ecommerce_app.repositories.UsuarioRepository;
import com.andres.app.ecommerce.ecommerce_app.services.ClienteService;
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
    ClienteRepository clienteRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void agregarUsuario(Usuario usuario, boolean isCliente) {
        usuario.setContrasenia(passwordEncoder.encode(usuario.getContrasenia()));
        Usuario usuarioConfirmacion = repository.save(usuario);
        if (isCliente){
            Cliente cliente = new Cliente();
            cliente.setUsuario(usuarioConfirmacion);
            clienteRepository.save(cliente);
        }
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
            repository.save(usuario);
        });
        return usuarioOptional;
    }

    @Override
    public boolean validarNombreUsuario(String nombreUsuario) {
        return repository.existsByNombreUsuario(nombreUsuario);
    }
}
