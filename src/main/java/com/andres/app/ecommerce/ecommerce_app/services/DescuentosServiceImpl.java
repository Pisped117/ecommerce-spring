package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.Descuentos;
import com.andres.app.ecommerce.ecommerce_app.repositories.DescuentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DescuentosServiceImpl implements DescuentosService{

    @Autowired
    DescuentoRepository repository;

    @Transactional
    @Override
    public Descuentos agregarDescuento(Descuentos descuento) {
        return repository.save(descuento);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Descuentos> listarDescuentos() {
        return (List<Descuentos>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Descuentos> buscarDescuentoPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Optional<Descuentos> eliminarDescuento(Long id) {
        Optional<Descuentos> descuentosOptional = repository.findById(id);
        descuentosOptional.ifPresent(descuentos -> repository.deleteById(id));
        return descuentosOptional;
    }

    @Transactional
    @Override
    public Optional<Descuentos> actualizarDescuento(Long id, Descuentos descuento) {
        Optional<Descuentos> descuentos = repository.findById(id);
        descuentos.ifPresent(descuentoBd ->{
            descuento.setIdDescuento(descuentoBd.getIdDescuento());
            repository.save(descuento);
        });

        return descuentos;
    }
}
