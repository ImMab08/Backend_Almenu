package org.example.backend_almenu.controller.usuario;

import org.apache.coyote.Response;
import org.example.backend_almenu.dto.usuario.HeaderInfoUsuario;
import org.example.backend_almenu.dto.usuario.SettingsInfoUsuario;
import org.example.backend_almenu.model.usuario.Usuario;
import org.example.backend_almenu.repository.UsuarioRepository;
import org.example.backend_almenu.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/v01/user/")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    UsuarioRepository usuarioRepository;

    // Traer los datos de todos los usuarios.
    @GetMapping("users")
    public List<Usuario> getUsuario() {
        return usuarioService.usuario();
    }

    // Traer los datos de un usuario con su email.
    @GetMapping("user-email/{email}")
    public ResponseEntity<?> getUsuarioEmail(@PathVariable String email, @AuthenticationPrincipal Usuario usuarioAuthenticated) {
        String authenticatedEmail = usuarioAuthenticated.getEmail();

        if (!authenticatedEmail.equals(email)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("No tienes acceso a esta información");
        }

        Optional<Usuario> usuario = usuarioService.getUsuarioEmail(email);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("navboard")
    public HeaderInfoUsuario getHeaderInfoUsuarioDto(Authentication authentication) {
        return usuarioService.getHeaderInfoUsuarioDto(authentication);
    }

    // Traer datos del usuario con su email para los settings.
    @GetMapping("settings")
    public SettingsInfoUsuario getSettingsInfoUsuarioDto(Authentication authentication) {
        return usuarioService.getSettingsInfoUsuarioDto(authentication);
    }

    // Actualizar un usuario.
    @PutMapping("update/{id_usuario}")
    public ResponseEntity<?> updateUsuario(@PathVariable("id_usuario") int id_usuario, @RequestBody Usuario usuario, Authentication authentication) {
        try {
            String mensaje = usuarioService.updateUsuario(id_usuario, usuario, authentication);
            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro al crear un usuari");
        }
    }

    // Eliminar un usuario.
    @DeleteMapping("delete/{id_usuario}")
    public String deleteUsuario(@PathVariable("id_usuario") int id_usuario, Authentication authentication) {
        String mensaje = usuarioService.deleteUsuario(id_usuario, authentication);
        return mensaje;
    }
}
