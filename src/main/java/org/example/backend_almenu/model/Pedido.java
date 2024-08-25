package org.example.backend_almenu.model;

import org.example.backend_almenu.model.*;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "pedido", schema = "almenu")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente idCliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado")
    private Empleado idEmpleado;

    @Column(name = "fecha_horaPedido")
    private Instant fechaHorapedido;

    @Column(name = "estado", length = 45)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numero_mesa")
    private Mesa numeroMesa;

    @OneToMany(mappedBy = "idPedido")
    private Set<Factura> facturas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idPedido")
    private Set<PedidoProducto> pedidoProductos = new LinkedHashSet<>();

}