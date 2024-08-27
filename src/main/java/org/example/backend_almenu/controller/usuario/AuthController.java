package org.example.backend_almenu.controller.usuario;

import lombok.RequiredArgsConstructor;
import org.example.backend_almenu.dto.usuario.AuthResponse;
import org.example.backend_almenu.dto.usuario.LoginUsuario;
import org.example.backend_almenu.dto.usuario.RegisterUsuario;
import org.example.backend_almenu.service.usuario.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<AuthResponse> AutRegisterUsuario(@RequestBody RegisterUsuario request) {
        return ResponseEntity.ok(authService.register(request));
    }


    @PostMapping("login")
    public ResponseEntity<AuthResponse> LoginUsuario(@RequestBody LoginUsuario request) {
        return ResponseEntity.ok(authService.login(request));
    }


}
