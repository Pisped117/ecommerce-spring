package com.andres.app.ecommerce.ecommerce_app.controllers;

import com.andres.app.ecommerce.ecommerce_app.exceptions.NoExisteRegistroException;
import com.andres.app.ecommerce.ecommerce_app.models.Direccion;
import com.andres.app.ecommerce.ecommerce_app.models.Orden;
import com.andres.app.ecommerce.ecommerce_app.services.DireccionService;
import com.andres.app.ecommerce.ecommerce_app.services.OrdenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/direccion")
public class DireccionController {

    @Value("${controller.direccion.message}")
    private String MESSAGE_ERROR;

    @Autowired
    DireccionService service;

    //Se hace inyecion de la clase de error para validacion
    @Autowired
    ValidationError error;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarDireccion(@Valid @RequestBody Direccion direccion, BindingResult result){
        if (result.hasFieldErrors()){
            return error.validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.agregarDireccion(direccion));
    }

    @GetMapping("/consultar")
    public List<Direccion> listarDireccion(){
        return service.listarDireccion();
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> listarDireccionPorId(@PathVariable Long id){
        Direccion direccion = service.buscarDireccionPorId(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.valueOf(200)).body(direccion);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Direccion> actualizarDireccion(@Valid @RequestBody Direccion direccion, BindingResult result, @PathVariable Long id){
        Direccion direccionDb  = service.actualizarDireccion(id, direccion).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(direccionDb);


    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarDireccion(@PathVariable Long id){
        Direccion direccion = service.eliminarDireccion(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(direccion);

    }
}
