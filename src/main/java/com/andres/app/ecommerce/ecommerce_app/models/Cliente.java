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
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @OneToOne
    @JoinColumn(name = "idPersona")
    private Persona idPersona;

    @OneToOne(mappedBy = "idCliente", cascade = CascadeType.ALL)
    private ClienteFrecuente clienteFrecuente;

    @OneToMany(mappedBy = "idCliente", cascade = CascadeType.ALL)
    private List<Orden> ordenes;
}
