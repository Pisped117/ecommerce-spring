package com.andres.app.ecommerce.ecommerce_app.services;

import com.andres.app.ecommerce.ecommerce_app.models.TipoDocumento;
import com.andres.app.ecommerce.ecommerce_app.repositories.TipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService{

    @Autowired
    TipoDocumentoRepository repository;

    @Transactional
    @Override
    public TipoDocumento agregarTipoDocumento(TipoDocumento tipoDocumento) {
        return repository.save(tipoDocumento);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TipoDocumento> listarTipoDocumento() {
        return (List<TipoDocumento>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<TipoDocumento> buscarTipoDocumentoPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Optional<TipoDocumento> eliminarTipoDocumento(Long id) {
        Optional<TipoDocumento> tipoDocumentoOptional = repository.findById(id);
        tipoDocumentoOptional.ifPresent(tipoDocumento -> repository.deleteById(id));
        return tipoDocumentoOptional;
    }

    @Transactional
    @Override
    public Optional<TipoDocumento> actualizarTipoDocumento(Long id, TipoDocumento tipoDocumento) {
        Optional<TipoDocumento> tipoDocumentoOptional = repository.findById(id);
        tipoDocumentoOptional.ifPresent(tipoDocumentoDb -> {
            tipoDocumento.setIdTipoDocumento(id);
            repository.save(tipoDocumento);
            });
        return tipoDocumentoOptional;
    }
}
