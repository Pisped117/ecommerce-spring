package com.andres.app.ecommerce.ecommerce_app.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "{notblank.message}")
    @Size(min = 1, max = 60)
    private String numeroDocumento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idTipoDocumento",nullable = false)
    private TipoDocumento tipoDocumento;

    @NotBlank(message = "{notblank.message}")
    @Size(min = 1, max = 60)
    private String nombre;

    @NotBlank(message = "{notblank.message}")
    @Size(min = 1, max = 60)
    private String apellido;

    private String telefono;

    @Email
    private String correo;

    private Date fechaCreacion;




}
