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
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "idCategoriaProducto")
    private CategoriaProducto idCategoriaProducto;

    @OneToMany(mappedBy = "idProducto", cascade = CascadeType.ALL)
    private List<DetalleOrden> detalleOrdenes;



}
