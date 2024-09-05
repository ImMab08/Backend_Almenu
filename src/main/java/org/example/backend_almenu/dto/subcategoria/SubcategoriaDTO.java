package org.example.backend_almenu.dto.subcategoria;

import lombok.Data;

@Data
public class SubcategoriaDTO {

    private Integer id_categoria;
    private Integer id_subcategoria;
    private String nombreCategoria;
    private String email;
    private String nombre;
    private String descripcion;

}
