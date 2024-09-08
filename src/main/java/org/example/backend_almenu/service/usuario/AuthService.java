package org.example.backend_almenu.service.usuario;

import lombok.RequiredArgsConstructor;
import org.example.backend_almenu.dto.usuario.AuthResponse;
import org.example.backend_almenu.dto.usuario.LoginRequest;
import org.example.backend_almenu.dto.usuario.RegisterRequest;
import org.example.backend_almenu.model.usuario.PlanUsuario;
import org.example.backend_almenu.model.usuario.Roles;
import org.example.backend_almenu.model.usuario.Usuario;
import org.example.backend_almenu.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        Usuario usuario = usuarioRepository.getEmail(loginRequest.getEmail());
        String token = jwtService.getToken(usuario);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        Usuario usuario = Usuario.builder()
                .nombre(registerRequest.getNombre())
                .apellido(registerRequest.getApellido())
                .celular(registerRequest.getCelular())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .activo(true)
                .plan(PlanUsuario.gratuito)
                .build();

        usuarioRepository.save(usuario);
        return  AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();
    }
}
