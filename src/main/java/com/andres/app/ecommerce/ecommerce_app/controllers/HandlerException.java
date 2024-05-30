package com.andres.app.ecommerce.ecommerce_app.controllers;

import com.andres.app.ecommerce.ecommerce_app.errors.Error;
import com.andres.app.ecommerce.ecommerce_app.exceptions.NoExisteRegistroException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class HandlerException {

    //Excepcion en caso de enviar campos con llaves foraneas vacias
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<?> llavesNulas(Exception e){

        //Llenamos el error creado en el modelo
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setError("Las llaves foraneas deben tener valor!!!");
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setDate(new Date());

        //Restornamos el cuerpo de la respuesta con un error 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(error);
    }
    //Se crea excepcion personalizada para cuando no se encuentren regsitros
    @ExceptionHandler(NoExisteRegistroException.class)
    public ResponseEntity<?> noExisteRegistro(Exception e){

        //Llenamos el error creado en el modelo
        Error error = new Error();
        error.setMessage(e.getMessage());
        error.setError("¡NO EXISTE EL REGSITRO INDICADO!");
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setDate(new Date());

        //Restornamos el cuerpo de la respuesta con un error 404
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


}
