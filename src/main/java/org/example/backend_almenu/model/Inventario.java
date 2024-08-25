package org.example.backend_almenu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "inventario", schema = "almenu")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventario", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "cantidad", nullable = false)
    private Float cantidad;

    @Column(name = "unidad", nullable = false, length = 50)
    private String unidad;

    @OneToMany(mappedBy = "idInventario")
    private Set<ProductoIngrediente> productoIngredientes = new LinkedHashSet<>();

}