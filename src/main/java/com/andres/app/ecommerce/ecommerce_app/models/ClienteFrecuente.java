package com.andres.app.ecommerce.ecommerce_app.models;

import jakarta.persistence.*;
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
    @JoinColumn(name = "idCliente")
    private Cliente idCliente;

    private Date fechaInicio;
    private Date fechaFin;
    private Boolean estado;

}
