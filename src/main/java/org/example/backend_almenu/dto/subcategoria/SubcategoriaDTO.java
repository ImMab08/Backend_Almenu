package org.example.backend_almenu.dto.subcategoria;

import lombok.Data;

@Data
public class SubcategoriaDTO {

    private String nombre;
    private String descripcion;

    private int id_usuario;
    private int id_categoria;

}
