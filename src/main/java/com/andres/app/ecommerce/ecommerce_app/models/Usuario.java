package com.andres.app.ecommerce.ecommerce_app.models;

import com.andres.app.ecommerce.ecommerce_app.validation.ExistsByUserName;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    //@ExistsByUserName
    @NotBlank(message = "{notblank.message}")
    @Size(min = 1, max = 60)
    private String nombreUsuario;

    @NotBlank(message = "{notblank.message}")
    @Size(min = 8)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String contrasenia;

    @OneToOne
    @JoinColumn(name = "idPersona")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "idRol")
    private Roles rol;

    private String fotoUsuario;

    @NotNull(message = "{notnull.message}")
    private Boolean estado;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuario_perfil", joinColumns = @JoinColumn(name = "id_usuario",
            referencedColumnName = "idUsuario"), inverseJoinColumns = @JoinColumn(name = "id_perfil",
            referencedColumnName = "idPerfil")
    )
    private List<Perfil> perfiles;

    public boolean isEnabled(){
        return estado;
    }


}
