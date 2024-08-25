package org.example.backend_almenu.model;

import org.example.backend_almenu.model.*;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "factura", schema = "almenu")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura", nullable = false)
    private Integer id;

    @Column(name = "fecha_horaPedido", nullable = false)
    private Instant fechaHorapedido;

    @Column(name = "montoTotal", nullable = false)
    private Float montoTotal;

    @Column(name = "metodo_pago", nullable = false)
    private String metodoPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido")
    private Pedido idPedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mesa")
    private Mesa idMesa;

}