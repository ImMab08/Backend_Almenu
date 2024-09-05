package org.example.backend_almenu.dto.producto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoDTO {

    private String email;

    private Integer id_producto;
    private String nombre;
    private String descripcion;
    private String imagen;
    private BigDecimal precio;
    private int cantidad;

    private int id_categoria;
    private String nombreCategoria;

    private int id_subcategoria;
    private String nombreSubcategoria;

}
