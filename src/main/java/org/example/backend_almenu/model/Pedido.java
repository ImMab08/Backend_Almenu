package org.example.backend_almenu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import org.example.backend_almenu.model.usuario.Usuario;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Entity
@Data
@Table(name = "pedido", schema = "almenu")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido", nullable = false)
    private Integer id;

    @Column(name = "fecha_pedido", nullable = false)
    private LocalDateTime fecha_pedido;

    @Column(name = "estado", nullable = false)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "id_mesa", referencedColumnName = "id_mesa", nullable = false)
    @JsonIgnore
    private Mesa mesa;

    @OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
    @JsonIgnore
    private Factura factura;

    @OneToMany(mappedBy = "pedido", cascade =  CascadeType.ALL)
    @JsonIgnore
    private List<DetallePedido> detallePedido;

}
