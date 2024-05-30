package com.andres.app.ecommerce.ecommerce_app.controllers;

import com.andres.app.ecommerce.ecommerce_app.models.Persona;
import com.andres.app.ecommerce.ecommerce_app.services.PersonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    PersonaService service;

    @Autowired
    ValidationError error;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarPersona(@Valid @RequestBody Persona persona, BindingResult result){
        if (result.hasFieldErrors()){
            return error.validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.agregarPersona(persona));
    }

    @GetMapping("/consultar")
    public List<Persona> listarPersonas(){
        return service.listarPersonas();
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<Persona> listarPersonaPorId(@PathVariable Long id){
        Optional<Persona> personaOptional = service.buscarPersonaPorId(id);
        if (personaOptional.isPresent()){
            return ResponseEntity.ok(personaOptional.orElseThrow());
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Persona> actualizarPersona(@Valid @RequestBody Persona persona, BindingResult result, @PathVariable Long id){
        Optional<Persona> personaOptional = service.actualizarPersona(id, persona);
        if (personaOptional.isPresent()){
            return ResponseEntity.ok(personaOptional.orElseThrow());
        }else {
            return ResponseEntity.notFound().build();
        }


    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPersona(@PathVariable Long id){
        Optional<Persona> personaOptional= service.eliminarPersona(id);
        if (personaOptional.isPresent()){
            return ResponseEntity.accepted().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }


}
