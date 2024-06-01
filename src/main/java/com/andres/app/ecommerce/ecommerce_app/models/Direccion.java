package com.andres.app.ecommerce.ecommerce_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDireccion;

    @NotBlank
    @Size(min = 8, max = 70)
    private String direccion;

    private String detalle;

    @ManyToOne
    @JoinColumn(name = "idLocalidad", nullable = false)
    private Localidad localidad;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private Cliente cliente;

}
