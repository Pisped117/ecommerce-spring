package com.andres.app.ecommerce.ecommerce_app.services.impl;

import com.andres.app.ecommerce.ecommerce_app.models.Persona;
import com.andres.app.ecommerce.ecommerce_app.models.Usuario;
import com.andres.app.ecommerce.ecommerce_app.repositories.ClienteRepository;
import com.andres.app.ecommerce.ecommerce_app.repositories.PersonaRepository;
import com.andres.app.ecommerce.ecommerce_app.services.PersonaService;
import com.andres.app.ecommerce.ecommerce_app.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository repository;

    @Autowired
    UsuarioService usuarioService;

    @Transactional
    @Override
    public Persona agregarPersona(Persona persona) {
        String numeroDocumento = repository.validarNumeroDocumento(persona.getNumeroDocumento());
        if (numeroDocumento != null){
            return null;
        }
        persona.setFechaCreacion(new Date());
        Persona personaConfirmation = repository.save(persona);

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(personaConfirmation.getNumeroDocumento() +"_usuario");
        usuario.setContrasenia(persona.getNumeroDocumento());
        usuario.setEstado(true);
        usuario.setPersona(personaConfirmation);

        usuarioService.agregarUsuario(usuario, persona.isCliente());

        return personaConfirmation;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Persona> listarPersonas() {
        return (List<Persona>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Persona> buscarPersonaPorId(Long id) {
        return repository.findById(id);

    }

    @Transactional
    @Override
    public Optional<Persona> eliminarPersona(Long id) {
        Optional<Persona> personaOptional = repository.findById(id);
        personaOptional.ifPresent(personaDb -> repository.deleteById(personaDb.getIdPersona()));
        return personaOptional;

    }

    @Transactional
    @Override
    public Optional<Persona> actualizarPersona(Long id, Persona persona) {
        Optional<Persona> personaOptional = repository.findById(id);
        personaOptional.ifPresent(personaDB -> {
            persona.setIdPersona(personaOptional.get().getIdPersona());
            //Se valida fecha para que no se actualice
            persona.setFechaCreacion(personaOptional.get().getFechaCreacion());
            repository.save(persona);
        });
        return personaOptional;
    }
}
