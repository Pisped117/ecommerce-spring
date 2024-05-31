package com.andres.app.ecommerce.ecommerce_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Descuentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDescuento;

    @NotNull(message = "notnull.message")
    private Double porcentajeDescuento;

    @ManyToOne
    @JoinColumn(name = "idTipoDescuento", nullable = false)
    private TipoDescuento tipoDescuento;

    //Se dejo flexibilidad en la tabla para manejar los varios tipos de descuentos
    //Con fechas de inicio y fin en caso de tratarse de un descuento por fechas
    //Con horas inicio y fin en caso de tratarse de un descuento por horas
    private Date fechaInicio;
    private Date fechaFin;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    @NotNull(message = "notnull.message")
    private Boolean estado;


}
