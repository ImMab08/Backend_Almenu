package org.example.backend_almenu.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String nombre;
    private String apellido;
    private String celular;
    private String email;
    private String password;
    private String activo;
    private String plan;

}