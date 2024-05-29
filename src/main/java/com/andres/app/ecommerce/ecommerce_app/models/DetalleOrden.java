package com.andres.app.ecommerce.ecommerce_app.models;

import jakarta.persistence.*;
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

    private Integer cantidad;
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "idOrden")
    private Orden idOrden;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto idProducto;
}
