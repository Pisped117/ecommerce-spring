package com.andres.app.ecommerce.ecommerce_app.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombreUsuario;
    private String contrasenia;

    @OneToOne
    @JoinColumn(name = "idPersona")
    private Persona idPersona;

    @ManyToOne
    @JoinColumn(name = "idRol")
    private Roles idRol;

    private String fotoUsuario;
    private Boolean estado;

    @ManyToMany(mappedBy = "usuarios")
    private List<Perfil> perfiles;

    @OneToMany(mappedBy = "idUsuario", cascade = CascadeType.ALL)
    private List<Orden> ordenes;


}
