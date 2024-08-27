package org.example.backend_almenu.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUsuario {

    private String nombre;
    private String apellido;
    private String celular;
    private String correo;
    private String password;

}
