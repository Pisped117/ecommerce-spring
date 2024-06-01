package com.andres.app.ecommerce.ecommerce_app.controllers;

import com.andres.app.ecommerce.ecommerce_app.exceptions.NoExisteRegistroException;
import com.andres.app.ecommerce.ecommerce_app.models.Descuentos;
import com.andres.app.ecommerce.ecommerce_app.models.Orden;
import com.andres.app.ecommerce.ecommerce_app.services.DescuentosService;
import com.andres.app.ecommerce.ecommerce_app.services.OrdenService;
import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orden")
public class OrdenController {

    @Value("${controller.orden.message}")
    private String MESSAGE_ERROR;

    @Autowired
    OrdenService service;

    //Se hace inyecion de la clase de error para validacion
    @Autowired
    ValidationError error;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarOrden(@Valid @RequestBody Orden orden, BindingResult result){
        if (result.hasFieldErrors()){
            return error.validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.agregarOrden(orden));
    }

    @GetMapping("/consultar")
    public List<Orden> listarOrden(){
        return service.listarOrden();
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> listarOrdenPorId(@PathVariable Long id){
        Orden orden = service.buscarOrdenPorId(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.valueOf(200)).body(orden);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Orden> actualizarOrden(@Valid @RequestBody Orden orden, BindingResult result, @PathVariable Long id){
        Orden ordenDb  = service.actualizarOrden(id, orden).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(ordenDb);


    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarDescuento(@PathVariable Long id){
        Orden orden = service.eliminarOrden(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(orden);

    }
}
