package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.Cliente;
import com.andres.app.ecommerce.ecommerce_app.models.ClienteFrecuente;
import com.andres.app.ecommerce.ecommerce_app.repositories.ClienteFrecuenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteFrecuenteServiceImpl implements ClienteFrecuenteService{

    @Autowired
    private ClienteFrecuenteRepository repository;

    @Transactional
    @Override
    public ClienteFrecuente agregarClienteFrecuente(ClienteFrecuente clienteFrecuente) {
        return repository.save(clienteFrecuente);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClienteFrecuente> listarClienteFrecuente() {
        return (List<ClienteFrecuente>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ClienteFrecuente> buscarClienteFrecuente(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Optional<ClienteFrecuente> eliminarClienteFrecuente(Long id) {
        Optional<ClienteFrecuente> clienteFrecuenteOptional = repository.findById(id);
        clienteFrecuenteOptional.ifPresent(clienteFrecuente -> repository.deleteById(id));
        return clienteFrecuenteOptional;
    }

    @Transactional
    @Override
    public Optional<ClienteFrecuente> actualizarClienteFrecuente(Long id, ClienteFrecuente clienteFrecuente) {
        Optional<ClienteFrecuente> clienteFrecuenteOptional = repository.findById(id);
        clienteFrecuenteOptional.ifPresent(clienteFrecuenteDb -> {
            clienteFrecuente.setIdClienteFrecuente(id);
            repository.save(clienteFrecuente);
        });
        return clienteFrecuenteOptional;
    }
}
