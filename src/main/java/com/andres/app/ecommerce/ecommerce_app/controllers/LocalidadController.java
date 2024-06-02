package com.andres.app.ecommerce.ecommerce_app.controllers;

import com.andres.app.ecommerce.ecommerce_app.exceptions.NoExisteRegistroException;
import com.andres.app.ecommerce.ecommerce_app.models.Localidad;
import com.andres.app.ecommerce.ecommerce_app.services.LocalidadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/localidad")
public class LocalidadController {

    @Value("${controller.localidad.message}")
    private String MESSAGE_ERROR;

    @Autowired
    LocalidadService service;

    //Se hace inyecion de la clase de error para validacion
    @Autowired
    ValidationError error;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarLocalidad(@Valid @RequestBody Localidad localidad, BindingResult result){
        if (result.hasFieldErrors()){
            return error.validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.agregarLocalidad(localidad));
    }

    @GetMapping("/consultar")
    public List<Localidad> listarLocalidad(){
        return service.listarLocalidad();
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> listarLocalidadProductoPorId(@PathVariable Long id){
        Localidad localidad = service.buscarLocalidadTorId(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(localidad);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Localidad> actualizarLocalidad(@Valid @RequestBody Localidad localidad, BindingResult result, @PathVariable Long id){
        Localidad localidadDb  = service.actualizarLocalidad(id, localidad).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(localidadDb);


    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarLocalidad(@PathVariable Long id){
        Localidad localidad = service.eliminarLocalidad(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(localidad);

    }
}
