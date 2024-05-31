package com.andres.app.ecommerce.ecommerce_app.controllers;

import com.andres.app.ecommerce.ecommerce_app.exceptions.NoExisteRegistroException;
import com.andres.app.ecommerce.ecommerce_app.models.Descuentos;
import com.andres.app.ecommerce.ecommerce_app.services.DescuentosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/descuentos")
public class DescuentosController {

    @Value("${controller.descuento.message}")
    private String MESSAGE_ERROR;

    @Autowired
    DescuentosService service;

    //Se hace inyecion de la clase de error para validacion
    @Autowired
    ValidationError error;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarDescuento(@Valid @RequestBody Descuentos descuento, BindingResult result){
        if (result.hasFieldErrors()){
            return error.validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.agregarDescuento(descuento));
    }

    @GetMapping("/consultar")
    public List<Descuentos> listarDescuentos(){
        return service.listarDescuentos();
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> listarDescuentoPorId(@PathVariable Long id){
        //Si la persona no existe se lanza un RunTimeException
        Descuentos descuento = service.buscarDescuentoPorId(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.valueOf(200)).body(descuento);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Descuentos> actualizarDescuento(@Valid @RequestBody Descuentos descuento, BindingResult result, @PathVariable Long id){
        //Si la persona no existe se lanza un RunTimeException
        Descuentos descuentoDb  = service.actualizarDescuento(id, descuento).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(descuentoDb);


    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarDescuento(@PathVariable Long id){
        //Si la persona no existe se lanza un RunTimeException
        Descuentos descuento = service.eliminarDescuento(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(descuento);

    }
}
