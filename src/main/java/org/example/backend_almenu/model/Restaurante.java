package org.example.backend_almenu.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Entity
@Data
@Table(name = "restaurante", schema = "almenu")
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_restaurante", nullable = false)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "ciudad", nullable = false)
    private String ciudad;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "logo")
    private String logo;

    @OneToMany(mappedBy = "restaurante")
    private Set<Mesa> mesas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "restaurante")
    private Set<Empleado> empleados = new LinkedHashSet<>();

    @OneToMany(mappedBy = "restaurante")
    private Set<Categoria> categorias = new LinkedHashSet<>();
}
