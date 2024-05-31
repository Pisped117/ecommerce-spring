package com.andres.app.ecommerce.ecommerce_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


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

    @NotBlank(message = "{notblank.message}")
    @Size(min = 1, max = 60)
    private String nombreUsuario;

    @NotBlank(message = "{notblank.message}")
    @Size(min = 8)
    private String contrasenia;

    @OneToOne
    @JoinColumn(name = "idPersona")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "idRol")
    private Roles rol;

    private String fotoUsuario;

    @NotNull(message = "{notnull.message}")
    private Boolean estado;


}
