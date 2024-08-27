package org.example.backend_almenu.service;

import org.example.backend_almenu.dto.usuario.HeaderInfoUsuario;
import org.example.backend_almenu.dto.usuario.SettingsInfoUsuario;
import org.example.backend_almenu.model.Usuario;
import org.example.backend_almenu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> usuario() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioEmail(String email) {
        return usuarioRepository.getEmail(email);
    }

    // Traer datos del usuario para el header del board
    public HeaderInfoUsuario getHeaderInfoUsuarioDto(String email) {

        Usuario usuario = usuarioRepository.getEmail(email);
        HeaderInfoUsuario dto = new HeaderInfoUsuario();

        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());

        return dto;
    }

    // Traer datos del usuario para el settings del board.
    public SettingsInfoUsuario getSettingsInfoUsuarioDto(String email) {

        Usuario usuario = usuarioRepository.getEmail(email);
        SettingsInfoUsuario dto = new SettingsInfoUsuario();

        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setCelular(usuario.getCelular());
        dto.setEmail(usuario.getEmail());

        return dto;

    }

    public String GuardarUsuario(Usuario usuario) {
        try {
            Usuario UserEmail = usuarioRepository.getEmail(usuario.getEmail());
            if (UserEmail != null) {
                return "El correo ingresado ya esta asociado a otra cuenta.";
            }

            usuarioRepository.save(usuario);
            return "Usuario registrado exitosamente.";

        } catch (Exception e) {
            return "Error al guardar al usuario" + e.getMessage();
        }
    }

    public String updateUsuario(Usuario usuario) {
        Usuario UserEmail = usuarioRepository.getEmail(usuario.getEmail());
        if (UserEmail != null) {

            UserEmail.setNombre(usuario.getNombre());
            UserEmail.setApellido(usuario.getApellido());
            UserEmail.setCelular(usuario.getCelular());
            UserEmail.setEmail(usuario.getEmail());
            UserEmail.setPassword(usuario.getPassword());

            usuarioRepository.save(UserEmail);
            return "Actualizado exitoso";

        } else {
            return "El usuario no existe";
        }
    }

    public String deleteUsuario(String email) {
        Usuario UserEmail = usuarioRepository.getEmail(email);
        if (UserEmail != null) {
            usuarioRepository.deleteById(UserEmail.getId());
            return "Eliminado exitosamente";
        } else {
            return "El usuario no existe";
        }
    }
}
