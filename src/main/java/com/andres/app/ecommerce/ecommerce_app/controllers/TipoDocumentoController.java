package com.andres.app.ecommerce.ecommerce_app.controllers;

import com.andres.app.ecommerce.ecommerce_app.exceptions.NoExisteRegistroException;
import com.andres.app.ecommerce.ecommerce_app.models.TipoDocumento;
import com.andres.app.ecommerce.ecommerce_app.services.TipoDocumentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo/documento")
public class TipoDocumentoController {

    @Value("${controller.tipo.documento.message}")
    private String MESSAGE_ERROR;

    @Autowired
    TipoDocumentoService service;

    //Se hace inyecion de la clase de error para validacion
    @Autowired
    ValidationError error;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarTipoDocumento(@Valid @RequestBody TipoDocumento tipoDocumento, BindingResult result){
        if (result.hasFieldErrors()){
            return error.validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.agregarTipoDocumento(tipoDocumento));
    }

    @GetMapping("/consultar")
    public List<TipoDocumento> listarTipoDocumento(){
        return service.listarTipoDocumento();
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> listarTipoDocumentoPorId(@PathVariable Long id){
        TipoDocumento tipoDocumento = service.buscarTipoDocumentoPorId(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(tipoDocumento);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<TipoDocumento> actualizarTipoDocumento(@Valid @RequestBody TipoDocumento tipoDocumento, BindingResult result, @PathVariable Long id){
        TipoDocumento tipoDocumentoDb  = service.actualizarTipoDocumento(id, tipoDocumento).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(tipoDocumentoDb);


    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTipoDocumento(@PathVariable Long id){
        TipoDocumento tipoDocumento = service.eliminarTipoDocumento(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(tipoDocumento);

    }

}
