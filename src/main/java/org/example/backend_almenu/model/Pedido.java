package org.example.backend_almenu.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Entity
@Data
@Table(name = "pedido", schema = "almenu")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido", nullable = false)
    private Integer id_pedido;

    @Column(name = "fecha_pedido", nullable = false)
    private String fechaPedido;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "id_mesa", nullable = false)
    private Mesa mesa;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;





}
