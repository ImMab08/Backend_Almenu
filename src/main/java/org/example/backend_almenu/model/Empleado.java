package org.example.backend_almenu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "empleado", schema = "almenu")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "salario")
    private Float salario;

    @OneToMany(mappedBy = "idEmpleado")
    private Set<Pedido> pedidos = new LinkedHashSet<>();

}