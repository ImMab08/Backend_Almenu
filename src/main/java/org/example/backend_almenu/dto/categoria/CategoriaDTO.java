package org.example.backend_almenu.dto.categoria;

import lombok.Data;

@Data
public class CategoriaDTO {

    private Integer id_categoria;
    private String email;
    private String nombre;
    private String descripcion;

}