package com.andres.app.ecommerce.ecommerce_app.controllers;

import com.andres.app.ecommerce.ecommerce_app.exceptions.DatoRepetidoException;
import com.andres.app.ecommerce.ecommerce_app.exceptions.NoExisteRegistroException;
import com.andres.app.ecommerce.ecommerce_app.models.Persona;
import com.andres.app.ecommerce.ecommerce_app.services.PersonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Value("${controller.persona.message}")
    private String MESSAGE_ERROR;

    @Value("${controller.persona.documento.repetido.message}")
    private String MESSAGE_ERROR_DOCUMENTO;


    @Autowired
    PersonaService service;

    @Autowired
    ValidationError error;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarPersona(@Valid @RequestBody Persona persona, BindingResult result){
        if (result.hasFieldErrors()){
            return error.validation(result);
        }
        Persona personaValidationDocument = service.agregarPersona(persona);
        //Validacion para que no se repita el numero de documento
        if (personaValidationDocument == null){
            throw new DatoRepetidoException(MESSAGE_ERROR_DOCUMENTO);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(persona);
    }

    @GetMapping("/consultar")
    public List<Persona> listarPersonas(){
        return service.listarPersonas();
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> listarPersonaPorId(@PathVariable Long id){
        Persona persona = service.buscarPersonaPorId(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.valueOf(200)).body(persona);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Persona> actualizarPersona(@Valid @RequestBody Persona persona, BindingResult result, @PathVariable Long id){
        Persona personaDb  = service.actualizarPersona(id, persona).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(personaDb);


    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPersona(@PathVariable Long id){
        Persona personaDb  = service.eliminarPersona(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
            return ResponseEntity.status(HttpStatus.OK).body(personaDb);

    }


}
