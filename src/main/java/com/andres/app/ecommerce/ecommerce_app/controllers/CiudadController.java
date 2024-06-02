package com.andres.app.ecommerce.ecommerce_app.controllers;

import com.andres.app.ecommerce.ecommerce_app.exceptions.NoExisteRegistroException;
import com.andres.app.ecommerce.ecommerce_app.models.CategoriaProducto;
import com.andres.app.ecommerce.ecommerce_app.models.Ciudad;
import com.andres.app.ecommerce.ecommerce_app.services.CategoriaProductoService;
import com.andres.app.ecommerce.ecommerce_app.services.CiudadService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ciudad")
public class CiudadController {

    @Value("${controller.ciudad.message")
    private String MESSAGE_ERROR;

    @Autowired
    CiudadService service;

    //Se hace inyecion de la clase de error para validacion
    @Autowired
    ValidationError error;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarCiudad(@Valid @RequestBody Ciudad ciudad, BindingResult result){
        if (result.hasFieldErrors()){
            return error.validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.agregarCiudad(ciudad));
    }

    @GetMapping("/consultar")
    public List<Ciudad> listarCiudad(){
        return service.listarCiudad();
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> listarCategoriaProductoPorId(@PathVariable Long id){
        Ciudad ciudad = service.buscarCiudadTorId(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(ciudad);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Ciudad> actualizarCiudad(@Valid @RequestBody Ciudad ciudad, BindingResult result, @PathVariable Long id){
        Ciudad ciudadDb  = service.actualizarCiudad(id, ciudad).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(ciudadDb);


    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarCiudad(@PathVariable Long id){
        Ciudad ciudad = service.eliminarCiudad(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(ciudad);

    }
}
