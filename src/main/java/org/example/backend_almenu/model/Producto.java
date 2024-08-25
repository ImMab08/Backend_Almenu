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
@Table(name = "productos", schema = "almenu")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private Categoria idCategoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_subcategoria")
    private Subcategoria idSubcategoria;

    @Column(name = "precio", nullable = false)
    private Float precio;

    @OneToMany(mappedBy = "idProducto")
    private Set<PedidoProducto> pedidoProductos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idProducto")
    private Set<ProductoIngrediente> productoIngredientes = new LinkedHashSet<>();

}