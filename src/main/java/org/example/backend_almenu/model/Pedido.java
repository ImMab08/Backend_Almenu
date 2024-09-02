package org.example.backend_almenu.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

//    @Column(name = "tipo", nullable = false)
//    private String tipo;

    @Column(name = "estado", nullable = false)
    private String estado;

//    @Column(name = "total", nullable = false)
//    private BigDecimal total;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_restaurante", referencedColumnName = "id_restaurante", nullable = false)
//    @JsonBackReference
//    private Restaurante restaurante;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_factura", referencedColumnName = "id_factura", nullable = false)
//    @JsonBackReference
//    private Factura factura;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_mesa", referencedColumnName = "id_mesa", nullable = false)
//    @JsonBackReference
//    private Mesa mesa;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado", nullable = false)
//    @JsonBackReference
//    private Empleado empleado;
}
