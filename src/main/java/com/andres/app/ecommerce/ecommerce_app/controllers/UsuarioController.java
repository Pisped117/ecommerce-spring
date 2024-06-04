package com.andres.app.ecommerce.ecommerce_app.controllers;

import com.andres.app.ecommerce.ecommerce_app.errors.ValidationError;
import com.andres.app.ecommerce.ecommerce_app.exceptions.DatoRepetidoException;
import com.andres.app.ecommerce.ecommerce_app.exceptions.NoExisteRegistroException;
import com.andres.app.ecommerce.ecommerce_app.models.Perfil;
import com.andres.app.ecommerce.ecommerce_app.models.Usuario;
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
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Value("${controller.usuario.repetido.message}")
    private String MESSAGE_USUARIO_EXISTENTE_ERROR;

    @Value("${controller.usuario.message}")
    private String MESSAGE_ERROR_USUARIO;

    @Value("${controller.perfil.message}")
    private String MESSAGE_ERROR_PERFIL;


    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PerfilService perfilService;

    //Se hace inyecion de la clase de error para validacion
    @Autowired
    ValidationError error;

   /* @PostMapping("/guardar")
    public ResponseEntity<?> guardarPersona(@Valid @RequestBody Usuario usuario, BindingResult result){
        if (result.hasFieldErrors()){
            return error.validation(result);
        }
        Usuario usuarioValidation = usuarioService.agregarUsuario(usuario);
        if (usuarioValidation == null){
            throw new DatoRepetidoException(MESSAGE_USUARIO_EXISTENTE_ERROR);
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioValidation);
    }*/

    //Metodo para asignar perfiles a usuarios
    @PostMapping("/asignar/usuario/perfil/{idUsuario}/{idPerfil}")
    public ResponseEntity<?> asignarPerfilAUsuario(@PathVariable Long idUsuario, @PathVariable Long idPerfil){
        Usuario usuario = usuarioService.buscarUsuaropPorId(idUsuario).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR_USUARIO));
        Perfil perfil = perfilService.buscarPerfilPorId(idPerfil).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR_PERFIL));
        usuario.getPerfiles().add(perfil);
        Optional<Usuario> usuarioActualizado = usuarioService.actualizarUsuario(idPerfil, usuario);
        return ResponseEntity.ok(usuarioActualizado);


    }

    @GetMapping("/consultar")
    public List<Usuario> listarPersonas(){
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<?> listarPersonaPorId(@PathVariable Long id){
        Usuario usuario = usuarioService.buscarUsuaropPorId(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR_USUARIO + id));
        return ResponseEntity.status(HttpStatus.valueOf(200)).body(usuario);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Usuario> actualizarPersona(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id){
        Usuario usuarioDb  = usuarioService.actualizarUsuario(id, usuario).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR_USUARIO + id));
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDb);


    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPersona(@PathVariable Long id){
        Usuario usuario  = usuarioService.eliminarUsuario(id).orElseThrow(() -> new NoExisteRegistroException(MESSAGE_ERROR_USUARIO + id));
        return ResponseEntity.status(HttpStatus.OK).body(usuario);

    }

}
