package com.andres.app.ecommerce.ecommerce_app.controllers;

import com.andres.app.ecommerce.ecommerce_app.exceptions.NoExisteRegistroException;
import com.andres.app.ecommerce.ecommerce_app.models.Roles;
import com.andres.app.ecommerce.ecommerce_app.models.TipoDocumento;
import com.andres.app.ecommerce.ecommerce_app.services.RolesService;
import com.andres.app.ecommerce.ecommerce_app.services.TipoDocumentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesController {

    @Value("${controller.rol.message}")
    private String MESSAGE_ERROR;

    @Autowired
    RolesService service;

    //Se hace inyecion de la clase de error para validacion
    @Autowired
    ValidationError error;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarRoles(@Valid @RequestBody Roles roles, BindingResult result){
        if (result.hasFieldErrors()){
            return error.validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.agregarRoles(roles));
    }

    @GetMapping("/consultar")
    public List<Roles> listarRoles(){
        return service.listarRoles();
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> listarRolPorId(@PathVariable Long id){
        Roles roles = service.buscarRolesPorId(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(roles);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Roles> actualizarRol(@Valid @RequestBody Roles roles, BindingResult result, @PathVariable Long id){
        Roles rolesDb  = service.actualizarRoles(id, roles).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(rolesDb);


    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarRol(@PathVariable Long id){
        Roles roles = service.eliminarRoles(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR + id));
        return ResponseEntity.status(HttpStatus.OK).body(roles);

    }
}
