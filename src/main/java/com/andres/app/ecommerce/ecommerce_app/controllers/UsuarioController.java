package com.andres.app.ecommerce.ecommerce_app.controllers;

import com.andres.app.ecommerce.ecommerce_app.exceptions.NoExisteRegistroException;
import com.andres.app.ecommerce.ecommerce_app.models.Usuario;
import com.andres.app.ecommerce.ecommerce_app.services.UsuarioServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Value("${controller.usuario.message}")
    private String MESSAGE_ERROR;

    @Autowired
    UsuarioServicio service;

    //Se hace inyecion de la clase de error para validacion
    @Autowired
    ValidationError error;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarPersona(@Valid @RequestBody Usuario usuario, BindingResult result){
        if (result.hasFieldErrors()){
            return error.validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.agregarUsuario(usuario));
    }

    @GetMapping("/consultar")
    public List<Usuario> listarPersonas(){
        return service.listarUsuarios();
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> listarPersonaPorId(@PathVariable Long id){
        Usuario usuario = service.buscarUsuaropPorId(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.valueOf(200)).body(usuario);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Usuario> actualizarPersona(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id){
        Usuario usuarioDb  = service.actualizarUsuario(id, usuario).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDb);


    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPersona(@PathVariable Long id){
        Usuario usuario  = service.eliminarUsuario(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(usuario);

    }

}
