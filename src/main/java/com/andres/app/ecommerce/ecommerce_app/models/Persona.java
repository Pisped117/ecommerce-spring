package com.andres.app.ecommerce.ecommerce_app.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;

    @NotBlank
    @Size(min = 1, max = 60)
    private String numeroDocumento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idTipoDocumento",nullable = false)
    private TipoDocumento tipoDocumento;

    @NotBlank
    @Size(min = 1, max = 60)
    private String nombre;

    @NotBlank
    @Size(min = 1, max = 60)
    private String apellido;

    private String telefono;

    @Email
    private String correo;

    @NotBlank
    @Size(min = 1, max = 60)
    private String direccion;

    @FutureOrPresent
    private Date fechaCreacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCiudad",nullable = false)
    private Ciudad ciudad;


}
