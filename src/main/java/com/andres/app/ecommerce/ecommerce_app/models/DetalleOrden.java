package com.andres.app.ecommerce.ecommerce_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "detalle_orden")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DetalleOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleOrden;

    @NotNull
    private Integer cantidad;

    @NotNull
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "idOrden", nullable = false)
    private Orden orden;

    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto producto;
}
