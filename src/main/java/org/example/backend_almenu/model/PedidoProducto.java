package org.example.backend_almenu.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pedido_producto", schema = "almenu")
public class PedidoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto idProducto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido idPedido;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "valor", nullable = false)
    private Float valor;

    // Constructor, getters y setters
    public PedidoProducto() {
    }

    public PedidoProducto(Producto idProducto, Pedido idPedido, Integer cantidad, Float valor) {
        this.idProducto = idProducto;
        this.idPedido = idPedido;
        this.cantidad = cantidad;
        this.valor = valor;
    }
}
