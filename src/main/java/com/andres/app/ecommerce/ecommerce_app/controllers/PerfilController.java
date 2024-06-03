package com.andres.app.ecommerce.ecommerce_app.controllers;

import com.andres.app.ecommerce.ecommerce_app.errors.ValidationError;
import com.andres.app.ecommerce.ecommerce_app.exceptions.NoExisteRegistroException;
import com.andres.app.ecommerce.ecommerce_app.models.Perfil;
import com.andres.app.ecommerce.ecommerce_app.services.PerfilService;
import com.andres.app.ecommerce.ecommerce_app.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

    @Value("${controller.usuario.message}")
    private String MESSAGE_ERROR_USUARIO;

    @Value("${controller.perfil.message}")
    private String MESSAGE_ERROR_PERFIL;


    @Autowired
    private PerfilService perfilService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ValidationError error;

    @PostMapping("/guardar")
    public ResponseEntity<?> agregarPerfil(@Valid @RequestBody Perfil perfil, BindingResult result){
        if (result.hasFieldErrors()){
            return  error.validation(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(perfilService.agregarPerfil(perfil));
    }


    @GetMapping("/consultar")
    public List<Perfil> listarPerfil(){
        return perfilService.listarPerfil();
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> listarPerfilProductoPorId(@PathVariable Long id){
        Perfil perfil = perfilService.buscarPerfilPorId(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR_PERFIL + id));
        return ResponseEntity.status(HttpStatus.OK).body(perfil);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Perfil> actualizarPerfil(@Valid @RequestBody Perfil perfil, BindingResult result, @PathVariable Long id){
        Perfil perfilDb  = perfilService.actualizarPerfil(id, perfil).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR_PERFIL + id));
        return ResponseEntity.status(HttpStatus.OK).body(perfilDb);


    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPerfil(@PathVariable Long id){
        Perfil perfil = perfilService.eliminarPerfil(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR_PERFIL + id));
        return ResponseEntity.status(HttpStatus.OK).body(perfil);

    }

}
