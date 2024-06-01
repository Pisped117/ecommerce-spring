package com.andres.app.ecommerce.ecommerce_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "cliente_frecuente")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ClienteFrecuente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClienteFrecuente;

    @OneToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;

    @NotNull
    private Date fechaInicio;

    @NotNull
    private Date fechaFin;

    @NotNull
    private Boolean estado;

}
