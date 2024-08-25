package org.example.backend_almenu.model;

import org.example.backend_almenu.model.*;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "producto_ingrediente", schema = "almenu")
public class ProductoIngrediente {
    @EmbeddedId
    private ProductoIngredienteId id;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto idProducto;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_inventario", nullable = false)
    private Inventario idInventario;

    @Column(name = "cantidad", nullable = false)
    private Float cantidad;

}