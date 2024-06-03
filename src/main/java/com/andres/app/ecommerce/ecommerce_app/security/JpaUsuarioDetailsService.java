package com.andres.app.ecommerce.ecommerce_app.security;

import com.andres.app.ecommerce.ecommerce_app.models.Usuario;
import com.andres.app.ecommerce.ecommerce_app.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JpaUsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = repository.findByNombreUsuario(username);

        if (usuarioOptional.isEmpty()){
            throw new UsernameNotFoundException(String.format("Nombre de usuario %s no existe en el sistema", username));
        }

        Usuario usuario = usuarioOptional.orElseThrow();
        List<GrantedAuthority> authorities = usuario.getPerfiles().stream()
                .map(perfil -> new SimpleGrantedAuthority(perfil.getNombre()))
                .collect(Collectors.toList());


        return new User(usuario.getNombreUsuario(),
                usuario.getContrasenia(),
                usuario.isEnabled(),
                true,
                true,
                true,
                authorities);
    }
}
