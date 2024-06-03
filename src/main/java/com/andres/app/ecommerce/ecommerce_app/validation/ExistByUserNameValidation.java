package com.andres.app.ecommerce.ecommerce_app.validation;

import com.andres.app.ecommerce.ecommerce_app.services.UsuarioService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistByUserNameValidation implements ConstraintValidator<ExistsByUserName, String> {

    @Autowired
    private UsuarioService service;

    @Override
    public void initialize (ExistsByUserName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String nombreUsuario, ConstraintValidatorContext constraintValidatorContext) {

        if (service == null){
            throw new IllegalStateException("Usuario Service no esta siendo inyectado correctamente");
        }
        return !service.validarNombreUsuario(nombreUsuario);
    }
}
