package com.andres.app.ecommerce.ecommerce_app.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrden;

    private Date fechaVenta;
    private Double total;

    @ManyToOne
    @JoinColumn(name = "idDescuento")
    private Descuentos idDescuento;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario idUsuario;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente idCliente;

    @OneToMany(mappedBy = "idOrden", cascade = CascadeType.ALL)
    private List<DetalleOrden> detalleOrdenes;



}
