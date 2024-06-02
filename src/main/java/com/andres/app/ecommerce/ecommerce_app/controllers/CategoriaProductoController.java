package com.andres.app.ecommerce.ecommerce_app.controllers;

import com.andres.app.ecommerce.ecommerce_app.exceptions.NoExisteRegistroException;
import com.andres.app.ecommerce.ecommerce_app.models.CategoriaProducto;
import com.andres.app.ecommerce.ecommerce_app.services.CategoriaProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria/producto")
public class CategoriaProductoController {

    @Value("${controller.categoria.message}")
    private String MESSAGE_ERROR;

    @Autowired
    CategoriaProductoService service;

    //Se hace inyecion de la clase de error para validacion
    @Autowired
    ValidationError error;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarCategoriaProducto(@Valid @RequestBody CategoriaProducto categoriaProducto, BindingResult result){
        if (result.hasFieldErrors()){
            return error.validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.agregarCategoriaProducto(categoriaProducto));
    }

    @GetMapping("/consultar")
    public List<CategoriaProducto> listarCategoriaProducto(){
        return service.listarCategoriaProducto();
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> listarCategoriaProductoPorId(@PathVariable Long id){
        CategoriaProducto categoriaProducto = service.buscarCategoriaProductoPorId(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(categoriaProducto);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CategoriaProducto> actualizarCategoriaProducto(@Valid @RequestBody CategoriaProducto categoriaProducto, BindingResult result, @PathVariable Long id){
        CategoriaProducto categoriaProductoDb  = service.actualizarCategoriaProducto(id, categoriaProducto).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(categoriaProductoDb);


    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarCategoriaProducto(@PathVariable Long id){
        CategoriaProducto categoriaProductoo = service.eliminarCategoriaProducto(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(categoriaProductoo);

    }
}
