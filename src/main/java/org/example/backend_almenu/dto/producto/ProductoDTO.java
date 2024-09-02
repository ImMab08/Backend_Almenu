package org.example.backend_almenu.dto.producto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoDTO {

    private String nombre;
    private String descripcion;
    private String imagen;
    private BigDecimal precio;
    private int cantidad;

    private int id_restaurante;

    private int id_categoria;
    private String nombre_categoria;

    private int id_subcategoria;
    private String nombre_subcategoria;

}
