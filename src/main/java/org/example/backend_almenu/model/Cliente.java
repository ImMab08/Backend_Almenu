package org.example.backend_almenu.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

@Setter
@Entity
@Data
@Table(name = "cliente", schema = "almenu")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", nullable = false)
    private Integer id;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Column(name = "correo")
    private String correo;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "direccion")
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

}
