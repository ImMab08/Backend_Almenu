package org.example.backend_almenu.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Entity
@Data
@Table(name = "usuario", schema = "almenu")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "celular", nullable = false)
    private String celular;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "idUsuario")
    private Set<Suscripcion> suscripcions = new LinkedHashSet<>();

}