package org.example.backend_almenu.dto.usuario;

import lombok.Data;

@Data
public class SettingsInfoUsuario {

    private String id_usuario;
    private String nombre;
    private String apellido;
    private String email;
    private String celular;
    private String plan;

}
