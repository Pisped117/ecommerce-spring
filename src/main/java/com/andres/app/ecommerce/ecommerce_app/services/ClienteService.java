package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Cliente agregarCliente(Cliente cliente);

    List<Cliente> listarCliente();

    Optional<Cliente> buscarClienteTorId (Long id);

    Optional<Cliente> eliminarCliente(Long id);

    Optional<Cliente> actualizarCliente(Long id, Cliente cliente);
}
