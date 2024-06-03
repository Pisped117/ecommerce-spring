package com.andres.app.ecommerce.ecommerce_app.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.andres.app.ecommerce.ecommerce_app.JwtSecurityConfig.*;

public class JwtValidationFilter extends BasicAuthenticationFilter {

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        //Se obtiene el header de ls solicitud
        String header = request.getHeader(HEADER_AUTHORIZATION);

        //Se valida si el header es nulo o si el prefijo no es correcto, se rechaza la solicitud
        if (header == null || !header.startsWith(PREFIX_TOKEN)){
            chain.doFilter(request,response);
            return;
        }

        //Remplazamos el prefijo con un valor vacio, obtenemos solo el token
        String token = header.replace(PREFIX_TOKEN, "");

        try{
            //Se verifica la firma del token y se obtienen sus claims-claves, para ver la informacion contenida dentro
            Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();

            //Obtenemos el nombre de usuario dentro del claim
            String username = claims.getSubject();

            //Obtenemos los perfiles contenidos dentro del token
            Object authoritiesClaims = claims.get("authorities");

            //Convertimos los perfiles a un tipo Collection para luego usarlos en la autenticacion del token
            Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                    new ObjectMapper()
                            //Se añade el .addMixIn para convinar los dos constructores de las clases y que pueda leer el JSON
                            //Con el parametro authority
                            .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
                            //Para el SimpleGrantedValue, recibe un parametro de tipo role, asi que debemos convertirlo
                                    //en tipo authority apra que pueda leer los perfiles dle JSON, para ellos usamos
                                            //La clase abstrabta SimpleGratedAuthorityJsonCreator
                            .readValue(authoritiesClaims.toString().getBytes(), SimpleGrantedAuthority[].class));

            //Validamos el token con el username unicamente
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            //Continuamos con la cadena de los filtros
            chain.doFilter(request,response);

        }catch (JwtException e){
            //Se crea JSON de respuesta en caso de excepcion de JWT, token invalido
            Map<String, String> body = new HashMap<>();
            body.put("error", e.getMessage());
            body.put("message", "El JWT token no es valido!");

            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(CONTENT_TYPE);




        }



        }



}
