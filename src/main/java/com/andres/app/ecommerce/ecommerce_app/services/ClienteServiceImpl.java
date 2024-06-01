package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.Cliente;
import com.andres.app.ecommerce.ecommerce_app.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository repository;


    @Transactional
    @Override
    public Cliente agregarCliente(Cliente cliente) {
        return repository.save(cliente);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cliente> listarCliente() {
        return (List<Cliente>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Cliente> buscarClienteTorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Optional<Cliente> eliminarCliente(Long id) {
        Optional<Cliente> clienteOptional = repository.findById(id);
        clienteOptional.ifPresent(cliente -> repository.deleteById(id));
        return clienteOptional;
    }

    @Transactional
    @Override
    public Optional<Cliente> actualizarCliente(Long id, Cliente cliente) {
        Optional<Cliente> clienteOptional = repository.findById(id);
        clienteOptional.ifPresent(clienteDb -> {
            cliente.setIdCliente(id);
            repository.save(cliente);
        });
        return clienteOptional;
    }
}
