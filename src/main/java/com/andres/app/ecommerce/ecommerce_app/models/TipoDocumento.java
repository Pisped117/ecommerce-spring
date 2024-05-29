package com.andres.app.ecommerce.ecommerce_app.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tipo_documento")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TipoDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_doc")
    private Long idTipoDoc;

    private String nombre;

    @Column(length = 4)
    private String sigla;

    @OneToMany(mappedBy = "idTipoDocumento", cascade = CascadeType.ALL)
    private List<Persona> personas;



}