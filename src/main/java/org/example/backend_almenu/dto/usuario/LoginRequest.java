package org.example.backend_almenu.dto.usuario;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;

}
