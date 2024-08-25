package org.example.backend_almenu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "plan", schema = "almenu")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plan", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private Float precio;

    @OneToMany(mappedBy = "idPlan")
    private Set<Suscripcion> suscripcions = new LinkedHashSet<>();

}