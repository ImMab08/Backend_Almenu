package org.example.backend_almenu.controller.usuario;

import lombok.RequiredArgsConstructor;
import org.example.backend_almenu.dto.usuario.AuthResponse;
import org.example.backend_almenu.dto.usuario.LoginRequest;
import org.example.backend_almenu.dto.usuario.RegisterRequest;
import org.example.backend_almenu.service.usuario.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/v01/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping(value = "register")
    public ResponseEntity register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

}
