package com.andres.app.ecommerce.ecommerce_app;

import com.andres.app.ecommerce.ecommerce_app.security.JwtAuthenticationFilter;
import com.andres.app.ecommerce.ecommerce_app.security.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigApp {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Confirguracion de permisos de ingreso
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        try {
        return httpSecurity.authorizeHttpRequests(authz -> authz
                        //Indica que solo los administradores pueden acceder al recurso usuario
                        .requestMatchers("/persona/guardar").permitAll()
                        .requestMatchers("/**").hasRole("ADMINISTRADOR")
                        .requestMatchers(HttpMethod.GET,"/categoria/producto/**").hasAnyRole("VENDEDOR")
                        .requestMatchers("/producto/**").hasAnyRole("VENDEDOR")
                        .requestMatchers(HttpMethod.GET,"/detalle/orden/**").hasAnyRole("VENDEDOR")
                        .requestMatchers(HttpMethod.GET,"/orden/**").hasAnyRole("VENDEDOR")
                        .requestMatchers(HttpMethod.GET,"/descuentos/**").hasAnyRole("VENDEDOR")
                        .requestMatchers(HttpMethod.GET,"/persona/**").hasAnyRole("CLIENTE")
                        .requestMatchers(HttpMethod.GET,"/usuario/**").hasAnyRole("CLIENTE")
                        .requestMatchers(HttpMethod.PUT,"/usuario/**").hasAnyRole("CLIENTE")
                        .requestMatchers(HttpMethod.GET,"/reportes/**").hasAnyRole("ANALISTA")
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated())
                        .addFilter(new JwtAuthenticationFilter(this.authenticationManager()))
                        .addFilter(new JwtValidationFilter(this.authenticationManager()))
                        .csrf(config -> config.disable())
                        .sessionManagement(managment -> managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .build();
        }catch (Exception e){
            throw  new RuntimeException("Error en la configuracion de SecurityFilterChain", e);
        }


    }


}
