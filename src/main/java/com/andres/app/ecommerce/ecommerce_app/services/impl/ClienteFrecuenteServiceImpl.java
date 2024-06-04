package com.andres.app.ecommerce.ecommerce_app.services.impl;

import com.andres.app.ecommerce.ecommerce_app.models.Cliente;
import com.andres.app.ecommerce.ecommerce_app.models.ClienteFrecuente;
import com.andres.app.ecommerce.ecommerce_app.repositories.ClienteFrecuenteRepository;
import com.andres.app.ecommerce.ecommerce_app.repositories.OrdenRepository;
import com.andres.app.ecommerce.ecommerce_app.services.ClienteFrecuenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteFrecuenteServiceImpl implements ClienteFrecuenteService {

    @Autowired
    private ClienteFrecuenteRepository repository;

    @Autowired
    private OrdenRepository ordenRepository;



    @Transactional
    @Override
    public void agregarClienteFrecuente(List<Cliente> clientes) {

        if (!clientes.isEmpty()){
            for (Cliente clienteFrecuente: clientes){
                Long idCliente = repository.buscarClienteFrecuenteRegistrado(clienteFrecuente.getIdCliente());
                if (idCliente == null){
                ClienteFrecuente clienteFrecuenteInsert = new ClienteFrecuente();
                clienteFrecuenteInsert.setCliente(clienteFrecuente);
                clienteFrecuenteInsert.setEstado(true);
                repository.save(clienteFrecuenteInsert);
                }
            }
        }
    }

    @Transactional
    @Override
    public List<ClienteFrecuente> listarClienteFrecuente() {

        //Logica de metodo para buscar clientes frecuentes

        //Si el cliente tiene mas de 5 ordenes, se busca y se guarda en la tabla ClientesFrecuentes
        agregarClienteFrecuente(ordenRepository.buscarClientesFrecuentes());

        //Si hay clientes se comienza la iteracion


        //Luego se consulta la tabla que arma el reporte
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
