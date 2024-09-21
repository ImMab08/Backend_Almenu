package org.example.backend_almenu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import org.example.backend_almenu.model.usuario.Usuario;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Entity
@Data
@Table(name = "factura", schema = "almenu")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura", nullable = false)
    private Integer id;

    @Column(name = "fecha_factura", nullable = false)
    private LocalDateTime fecha_factura;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente", nullable = false)
    @JsonIgnore
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado", nullable = false)
    @JsonIgnore
    private Empleado empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mesa", referencedColumnName = "id_mesa", nullable = false)
    @JsonIgnore
    private Mesa mesa;

}
