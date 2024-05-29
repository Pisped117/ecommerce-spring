package com.andres.app.ecommerce.ecommerce_app.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tipo_descuento")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TipoDescuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoDescuento;

    private String nombre;
    private String descripcion;

    @OneToMany(mappedBy = "idTipoDescuento", cascade = CascadeType.ALL)
    private List<Descuentos> descuentos;
}
