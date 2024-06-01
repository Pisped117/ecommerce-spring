package com.andres.app.ecommerce.ecommerce_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tipo_descuento")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TipoDescuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoDescuento;

    @NotBlank
    private String nombre;
    private String descripcion;

}
