package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.TipoDescuento;
import com.andres.app.ecommerce.ecommerce_app.repositories.TipoDescuentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TipoDescuentoServiceImpl implements TipoDescuentoService{

    @Autowired
    private TipoDescuentoRepository repository;


    @Transactional
    @Override
    public TipoDescuento agregarTipoDescuento(TipoDescuento tipoDescuento) {
        return repository.save(tipoDescuento);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TipoDescuento> listarTipoDescuento() {
        return (List<TipoDescuento>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<TipoDescuento> buscarTipoDescuentoPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Optional<TipoDescuento> eliminarTipoDescuento(Long id) {
        Optional<TipoDescuento> tipoDescuentoOptional = repository.findById(id);
        tipoDescuentoOptional.ifPresent(tipoDescuento -> repository.deleteById(id));
        return tipoDescuentoOptional;
    }

    @Transactional
    @Override
    public Optional<TipoDescuento> actualizarTipoDescuento(Long id, TipoDescuento tipoDescuento) {
        Optional<TipoDescuento> tipoDescuentoOptional = repository.findById(id);
        tipoDescuentoOptional.ifPresent(tipoDescuentoDb -> {
            tipoDescuento.setIdTipoDescuento(id);
            repository.save(tipoDescuento);
        });
        return tipoDescuentoOptional;
    }
}
