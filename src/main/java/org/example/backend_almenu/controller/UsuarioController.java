package org.example.backend_almenu.controller;

import org.example.backend_almenu.dto.usuario.HeaderInfoUsuario;
import org.example.backend_almenu.dto.usuario.SettingsInfoUsuario;
import org.example.backend_almenu.model.Usuario;
import org.example.backend_almenu.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/users/")

public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    // Traer los datos de todos los usuarios.
    @GetMapping("user")
    public List<Usuario> getUsuario() {
        return usuarioService.usuario();
    }

    // Traer los datos de un usuario con su email.
    @GetMapping("user-email/{email}")
    public Usuario getUsuarioEmail(@PathVariable ("email") String email) {
        return usuarioService.getUsuarioEmail(email);
    }

    // Traer datos del usuario con su email para el header del board.
    @GetMapping("header-user/{email}")
    public HeaderInfoUsuario getHeaderInfoUsuarioDto(@PathVariable ("email") String email) {
        return usuarioService.getHeaderInfoUsuarioDto(email);
    }

    // Traer datos del usuario con su email para los settings.
    @GetMapping("settings-user/{email}")
    public SettingsInfoUsuario getSettingsInfoUsuarioDto(@PathVariable ("email") String email) {
        return usuarioService.getSettingsInfoUsuarioDto(email);
    }

    // Crear un nuevo usuario.
    @PostMapping("new-user")
    public String nuevoUsuario(@RequestBody Usuario usuario) {
        String mensaje = usuarioService.GuardarUsuario(usuario);
        return mensaje;
    }

    // Actualizar un usuario.
    @PutMapping("update-user")
    public String updateUsuario(@RequestBody Usuario usuario) {
        String mensaje = usuarioService.updateUsuario(usuario);
        return mensaje;
    }

    // Eliminar un usuario.
    @DeleteMapping("delete-user/{email}")
    public String deleteUsuario(@PathVariable ("email") String email) {
        String mensaje = usuarioService.deleteUsuario(email);
        return mensaje;
        // Hola mundo
    }
}
