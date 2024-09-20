package org.example.backend_almenu.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;
import org.example.backend_almenu.model.usuario.Usuario;

import java.util.List;

@Setter
@Entity
@Data
@Table(name = "restaurante", schema = "almenu")
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_restaurante", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "ciudad", nullable = false)
    private String ciudad;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "logo")
    private String logo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private Usuario usuario;

//    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Categoria> categoria;
//
//    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Producto> producto;
//
//    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Cliente> cliente;
//
//    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Empleado> empleado;
//
//    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<Pedido> pedido;
//
//    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Factura> factura;
//
//    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Mesa> mesa;

}
