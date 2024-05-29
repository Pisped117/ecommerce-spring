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
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCiudad;
    private String nombre;

    @OneToMany(mappedBy = "idCiudad", cascade = CascadeType.ALL)
    private List<Persona> personas;
}
