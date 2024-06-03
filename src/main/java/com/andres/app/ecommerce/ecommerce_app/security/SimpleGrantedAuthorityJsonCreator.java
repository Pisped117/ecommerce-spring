package com.andres.app.ecommerce.ecommerce_app.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthorityJsonCreator {

    @JsonCreator
    //Modificamos el constructor de SimpleGrantedAuthorityJsonCreator para que convierta el authority en un role
    public SimpleGrantedAuthorityJsonCreator(@JsonProperty("authority") String role){

    }
}
