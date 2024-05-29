package com.andres.app.ecommerce.ecommerce_app.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

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

    private Double porcentajeDescuento;

    @ManyToOne
    @JoinColumn(name = "idTipoDescuento")
    private TipoDescuento idTipoDescuento;

    private Date fechaInicio;
    private Date fechaFin;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Boolean estado;

    @OneToMany(mappedBy = "idDescuento", cascade = CascadeType.ALL)
    private List<Orden> ordenes;


}
