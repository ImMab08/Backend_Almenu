package org.example.backend_almenu.dto.categoria;

import lombok.Data;

@Data
public class CategoriaDTO {

    private String nombre;
    private String descripcion;

    private int id_usuario;
    private int id_restaurante;

}

