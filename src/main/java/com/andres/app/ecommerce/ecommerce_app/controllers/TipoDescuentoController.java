package com.andres.app.ecommerce.ecommerce_app.controllers;

import com.andres.app.ecommerce.ecommerce_app.exceptions.NoExisteRegistroException;
import com.andres.app.ecommerce.ecommerce_app.models.Roles;
import com.andres.app.ecommerce.ecommerce_app.models.TipoDescuento;
import com.andres.app.ecommerce.ecommerce_app.services.TipoDescuentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo/descuento")
public class TipoDescuentoController {

    @Value("${controller.tipo.descuento.message}")
    private String MESSAGE_ERROR;

    @Autowired
    TipoDescuentoService service;

    //Se hace inyecion de la clase de error para validacion
    @Autowired
    ValidationError error;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarTipoDescuento(@Valid @RequestBody TipoDescuento tipoDescuento, BindingResult result){
        if (result.hasFieldErrors()){
            return error.validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.agregarTipoDescuento(tipoDescuento));
    }

    @GetMapping("/consultar")
    public List<TipoDescuento> listarTipoDescuento(){
        return service.listarTipoDescuento();
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> listarTipoDescuentoPorId(@PathVariable Long id){
        TipoDescuento tipoDescuento = service.buscarTipoDescuentoPorId(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(tipoDescuento);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<TipoDescuento> actualizarTipoDescuento(@Valid @RequestBody TipoDescuento tipoDescuento, BindingResult result, @PathVariable Long id){
        TipoDescuento tipoDescuentoDb  = service.actualizarTipoDescuento(id, tipoDescuento).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(tipoDescuentoDb);


    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTipoDescuento(@PathVariable Long id){
        TipoDescuento tipoDescuento = service.eliminarTipoDescuento(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(tipoDescuento);

    }
}
