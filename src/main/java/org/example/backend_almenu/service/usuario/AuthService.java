package org.example.backend_almenu.service.usuario;

import lombok.RequiredArgsConstructor;
import org.example.backend_almenu.dto.usuario.AuthResponse;
import org.example.backend_almenu.dto.usuario.LoginUsuario;
import org.example.backend_almenu.dto.usuario.RegisterUsuario;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    public AuthResponse login(LoginUsuario request) {
        return null;
    }

    public AuthResponse register(RegisterUsuario request) {
        return null;
    }
}
