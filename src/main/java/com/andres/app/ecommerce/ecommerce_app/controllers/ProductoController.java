package com.andres.app.ecommerce.ecommerce_app.controllers;

import com.andres.app.ecommerce.ecommerce_app.exceptions.NoExisteRegistroException;
import com.andres.app.ecommerce.ecommerce_app.models.Producto;
import com.andres.app.ecommerce.ecommerce_app.services.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Value("${controller.descuento.message}")
    private String MESSAGE_ERROR;

    @Autowired
    ProductoService service;

    //Se hace inyecion de la clase de error para validacion
    @Autowired
    ValidationError error;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarProducto(@Valid @RequestBody Producto producto, BindingResult result){
        if (result.hasFieldErrors()){
            return error.validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.agregarProducto(producto));
    }

    @GetMapping("/consultar")
    public List<Producto> listarProductos(){
        return service.listarProducto();
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> listarProductoPorId(@PathVariable Long id){
        Producto producto = service.buscarProductoPorId(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.valueOf(200)).body(producto);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Producto> actualizarProducto(@Valid @RequestBody Producto producto, BindingResult result, @PathVariable Long id){
        Producto productoBd  = service.actualizarProdcuto(id, producto).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(productoBd);


    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
        Producto producto = service.eliminarProducto(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(producto);

    }

}
