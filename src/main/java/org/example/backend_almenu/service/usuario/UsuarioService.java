package org.example.backend_almenu.service.usuario;

import org.example.backend_almenu.dto.usuario.HeaderInfoUsuario;
import org.example.backend_almenu.dto.usuario.SettingsInfoUsuario;
import org.example.backend_almenu.model.usuario.Usuario;
import org.example.backend_almenu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> usuario() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    // Traer datos del usuario para el header del board
    public HeaderInfoUsuario getHeaderInfoUsuarioDto(Authentication authentication) {
        String email = authentication.getName();

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        Usuario usuario = usuarioOpt.get();

        HeaderInfoUsuario dto = new HeaderInfoUsuario();
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setPlan(String.valueOf(usuario.getPlan()));

        return dto;
    }

    // Traer datos del usuario para el settings del board.
    public SettingsInfoUsuario getSettingsInfoUsuarioDto(Authentication authentication) {
        String email = authentication.getName();

        Optional<Usuario> usuarioOtp = usuarioRepository.findByEmail(email);
        if (usuarioOtp.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado") ;
        }

        Usuario usuario = usuarioOtp.get();
        SettingsInfoUsuario dto = new SettingsInfoUsuario();

        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setCelular(usuario.getCelular());
        dto.setEmail(usuario.getEmail());
        dto.setPlan(String.valueOf(usuario.getPlan()));

        return dto;
    }

    // Guardar un nuevo usuario
    public String GuardarUsuario(Usuario usuario) {
        try {
            Optional<Usuario> UserEmailOpt = usuarioRepository.findByEmail(usuario.getEmail());
            if (UserEmailOpt.isPresent()) {
                return "El correo ingresado ya esta asociado a otra cuenta.";
            }

            usuarioRepository.save(usuario);
            return "Usuario registrado exitosamente.";

        } catch (Exception e) {
            return "Error al guardar al usuario" + e.getMessage();
        }
    }

    // Actualizar un usuario
    public String updateUsuario(Usuario usuario) {

        Optional<Usuario> UsuarioEmailOpt = usuarioRepository.findByEmail(usuario.getEmail());
        if (UsuarioEmailOpt.isPresent()) {

            Usuario UserEmail = UsuarioEmailOpt.get();

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

    // Eliminar un usuario
    public String deleteUsuario(String email) {
        Optional<Usuario> UsuarioEmailOpt = usuarioRepository.findByEmail(email);
        if (UsuarioEmailOpt.isPresent()) {
            usuarioRepository.deleteById(UsuarioEmailOpt.get().getId());
            return "Eliminado exitosamente";
        } else {
            return "El usuario no existe";
        }
    }
}
