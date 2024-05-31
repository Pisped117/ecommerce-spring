package com.andres.app.ecommerce.ecommerce_app.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categoria_producto")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoriaProducto;

    private String nombreCategoria;
    private String descripcion;

}
