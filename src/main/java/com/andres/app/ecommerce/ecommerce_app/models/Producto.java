package com.andres.app.ecommerce.ecommerce_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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

    @NotBlank
    private String nombre;

    private String descripcion;

    @NotNull
    private Double precio;

    @NotNull
    private Integer stock;

    @NotNull
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "idCategoriaProducto", nullable = false)
    private CategoriaProducto categoriaProducto;




}
