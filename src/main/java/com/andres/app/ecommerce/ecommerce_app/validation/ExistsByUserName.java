package com.andres.app.ecommerce.ecommerce_app.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistByUserNameValidation.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsByUserName {

    String message() default "ya existe este nombre de usuario, por favor escoja otro";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
