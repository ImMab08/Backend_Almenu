package org.example.backend_almenu.controller.usuario;

import lombok.RequiredArgsConstructor;
import org.example.backend_almenu.dto.usuario.AuthResponse;
import org.example.backend_almenu.dto.usuario.LoginRequest;
import org.example.backend_almenu.dto.usuario.RegisterRequest;
import org.example.backend_almenu.service.usuario.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/v01/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        return ResponseEntity.ok(authService.login(loginRequest));

    }

    @PostMapping(value = "register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) {

        return ResponseEntity.ok(authService.register(registerRequest));

    }


}
