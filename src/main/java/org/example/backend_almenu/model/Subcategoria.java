package org.example.backend_almenu.model;

import org.example.backend_almenu.model.*;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "subcategoria", schema = "almenu")
public class Subcategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subcategoria", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private Categoria idCategoria;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "idSubcategoria")
    private Set<Producto> productos = new LinkedHashSet<>();

}