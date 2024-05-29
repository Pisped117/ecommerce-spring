package com.andres.app.ecommerce.ecommerce_app.models;


import jakarta.persistence.*;
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

    private String numeroDocumento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idTipoDoc")
    private TipoDocumento idTipoDocumento;

    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String direccion;
    private Date fechaCreacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCiudad")
    private Ciudad idCiudad;

    @OneToOne(mappedBy = "idPersona", cascade = CascadeType.ALL)
    private Cliente cliente;

    @OneToOne(mappedBy = "idPersona", cascade = CascadeType.ALL)
    private Usuario usuario;

}
