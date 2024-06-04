package com.andres.app.ecommerce.ecommerce_app.controllers;

import com.andres.app.ecommerce.ecommerce_app.errors.ValidationError;
import com.andres.app.ecommerce.ecommerce_app.exceptions.NoExisteRegistroException;
import com.andres.app.ecommerce.ecommerce_app.models.Ciudad;
import com.andres.app.ecommerce.ecommerce_app.models.DetalleOrden;
import com.andres.app.ecommerce.ecommerce_app.services.DetalleOrdenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalle/orden")
public class DetallesOrdenesController {

    @Value("${controller.detalle.orden.message")
    private String MESSAGE_ERROR;

    @Autowired
    private DetalleOrdenService service;

    @Autowired
    ValidationError error;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarDetallesOrden(@Valid @RequestBody DetalleOrden detalleOrden, BindingResult result){
        if (result.hasFieldErrors()){
            return error.validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.agregarDetalleOrden(detalleOrden));
    }

    @GetMapping("/consultar")
    public List<DetalleOrden> listarDetalleOrden(){
        return service.listarDetalleOrden();
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> listarDetalleOrdenPorId(@PathVariable Long id){
        DetalleOrden detalleOrden = service.buscarDetalleOrdenPorId(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(detalleOrden);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<DetalleOrden> actualizarDetalleOrden(@Valid @RequestBody DetalleOrden detalleOrden, BindingResult result, @PathVariable Long id){
        DetalleOrden detalleOrdenDb  = service.actualizarDetalleOrden(id, detalleOrden).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(detalleOrdenDb);


    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarDetalleOrden(@PathVariable Long id){
        DetalleOrden detalleOrden = service.eliminarDetalleOrden(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(detalleOrden);

    }
}
