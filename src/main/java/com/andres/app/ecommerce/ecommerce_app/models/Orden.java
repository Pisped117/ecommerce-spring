package com.andres.app.ecommerce.ecommerce_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

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

    @NotNull
    private Double total;

    @ManyToOne
    @JoinColumn(name = "idDescuento", nullable = false)
    private Descuentos descuento;


    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;





}
