package com.andres.app.ecommerce.ecommerce_app.controllers;

import com.andres.app.ecommerce.ecommerce_app.errors.Error;
import com.andres.app.ecommerce.ecommerce_app.exceptions.DatoRepetidoException;
import com.andres.app.ecommerce.ecommerce_app.exceptions.NoExisteRegistroException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Date;

@RestControllerAdvice
public class HandlerException {

    @Value("${handler.llavesnulas.message}")
    private String ERROR_LLAVES_NULAS;

    @Value("${handler.llaves.no.existen.message}")
    private String ERROR_LLAVES_NO_EXISTEN;

    @Value("${handler.noexisteregistro.message}")
    private String ERROR_REGISTRO_NO_EXISTE;

    @Value("${handler.validacion.dato.existente.message}")
    private String ERROR_DATO_YA_EXISTE;

    @Value("${handler.servicio.inexistente.message}")
    private String ERROR_SERVICIO_NO_ENCONTRADO;


    //Excepcion en caso de enviar campos con llaves foraneas vacias
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<?> llavesNulas(Exception e){

        //Llenamos el error creado en el modelo
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setError(ERROR_LLAVES_NULAS);
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setDate(new Date());

        //Restornamos el cuerpo de la respuesta con un error 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
    }

    //Excepcion en caso de enviar campos con llaves foraneas vacias
    @ExceptionHandler({JpaObjectRetrievalFailureException.class, DataIntegrityViolationException.class})
    public ResponseEntity<?> llavesForaneasNoExisten(Exception e){

        //Llenamos el error creado en el modelo
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setError(ERROR_LLAVES_NO_EXISTEN);
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setDate(new Date());

        //Restornamos el cuerpo de la respuesta con un error 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
    }
    //Se crea excepcion personalizada para cuando no se encuentren regsitros
    @ExceptionHandler(NoExisteRegistroException.class)
    public ResponseEntity<?> noExisteRegistro(Exception e){

        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setError(ERROR_REGISTRO_NO_EXISTE);
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setDate(new Date());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    //Se crea excpecion personalizada para validar numero de documento en el registro de personas
    @ExceptionHandler(DatoRepetidoException.class)
    public ResponseEntity<?> numeroDocumentoRepetido(Exception e){

        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setError(ERROR_DATO_YA_EXISTE);
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setDate(new Date());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    //Se crea excpecion personalizada para validar numero de documento en el registro de personas
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> servicioNoEncontradoException(Exception e){

        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setError(ERROR_SERVICIO_NO_ENCONTRADO);
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setDate(new Date());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }



}
