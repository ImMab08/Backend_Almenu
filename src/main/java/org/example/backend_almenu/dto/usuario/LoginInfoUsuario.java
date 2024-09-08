package org.example.backend_almenu.dto.usuario;

import lombok.Data;

@Data
public class LoginInfoUsuario {
    private String token;
    private Usuario usuarioInfo;

    @Data
    public static class Usuario {
        private String email;
        private String contraseña;
    }
}
