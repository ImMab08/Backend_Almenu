package org.example.backend_almenu.dto.producto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    private int id_producto;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private int cantidad;
    private String imagen;

    private int idCategoria;
    private String nombreCategoria;

    private int idSubcategoria;
    private String nombreSubcategoria;

}
