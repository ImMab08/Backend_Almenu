package org.example.backend_almenu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "mesa", schema = "almenu")
public class Mesa {
    @Id
    @Column(name = "numero_mesa", nullable = false)
    private Integer id;

    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;

    @OneToMany(mappedBy = "idMesa")
    private Set<Factura> facturas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "numeroMesa")
    private Set<Pedido> pedidos = new LinkedHashSet<>();

}