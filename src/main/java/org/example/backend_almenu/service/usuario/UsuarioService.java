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

    // Traer datos del usuario para el settings del board.
    public SettingsInfoUsuario getSettingsInfoUsuarioDto(Authentication authentication) {
        String email = authentication.getName();

        Optional<Usuario> usuarioOtp = usuarioRepository.findByEmail(email);
        if (usuarioOtp.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado") ;
        }

        Usuario usuario = usuarioOtp.get();
        SettingsInfoUsuario dto = new SettingsInfoUsuario();

        dto.setId_usuario(String.valueOf(usuario.getId()));
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setCelular(usuario.getCelular());
        dto.setEmail(usuario.getEmail());
        dto.setPlan(String.valueOf(usuario.getPlan()));

        return dto;
    }

    // Actualizar un usuario.
    public String updateUsuario( int id_usuario, Usuario updateUsuario, Authentication authentication) {
        String email = authentication.getName();
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()) {

            Usuario usuario = usuarioOptional.get();
            if (usuario.getId() != id_usuario) {
                throw new RuntimeException("No estás autorizado para editar a este usuario.");
            }

            usuario.setNombre(updateUsuario.getNombre());
            usuario.setApellido(updateUsuario.getApellido());
            usuario.setCelular(updateUsuario.getCelular());
            usuario.setEmail(updateUsuario.getEmail());

            usuarioRepository.save(usuario);
            return "Usuario actualizado con éxito";
        } else {
            return "El usuario no existe";
        }
    }

    // Eliminar un usuario.
    public String deleteUsuario(int id_usuario, Authentication authentication) {
        String email = authentication.getName();
        Optional<Usuario> UsuarioEmailOpt = usuarioRepository.findByEmail(email);

        if (UsuarioEmailOpt.isPresent()) {

            Usuario getUsuario = UsuarioEmailOpt.get();
            if (getUsuario.getId() != id_usuario) {
                throw new RuntimeException("No autorizado para eliminar este usuario");
            }

            usuarioRepository.deleteById(id_usuario);
            return "Eliminado exitosamente";

        } else {
            return "El usuario no existe";
        }
    }
}
