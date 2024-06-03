package com.andres.app.ecommerce.ecommerce_app.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPerfil;

    @NotBlank
    private String nombre;
    private String descripcion;

  /*  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuario_perfil", joinColumns = @JoinColumn(name = "id_perfil",
            referencedColumnName = "idPerfil"), inverseJoinColumns = @JoinColumn(name = "id_usuario",
            referencedColumnName = "idUsuario")
    )
    private List<Usuario> usuarios;*/
}
