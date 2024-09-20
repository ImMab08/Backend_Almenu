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
@RequestMapping("/v01/usuario/")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    // Traer datos del usuario con su email para los settings.
    @GetMapping("settings")
    public SettingsInfoUsuario getSettingsInfoUsuarioDto(Authentication authentication) {
        return usuarioService.getSettingsInfoUsuarioDto(authentication);
    }

    // Actualizar un usuario.
    @PutMapping("update/{id_usuario}")
    public ResponseEntity<?> updateUsuario(@PathVariable("id_usuario") int id_usuario, @RequestBody Usuario updateUsuario, Authentication authentication) {
        try {
            String usuarioActualizado = usuarioService.updateUsuario(id_usuario, updateUsuario, authentication);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar al usuario");
        }
    }

    // Eliminar un usuario.
    @DeleteMapping("delete/{id_usuario}")
    public String deleteUsuario(@PathVariable("id_usuario") int id_usuario, Authentication authentication) {
        String mensaje = usuarioService.deleteUsuario(id_usuario, authentication);
        return mensaje;
    }
}
