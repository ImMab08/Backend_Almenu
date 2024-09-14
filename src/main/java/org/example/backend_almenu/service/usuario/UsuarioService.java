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

    // Actualizar un usuario
    public String updateUsuario(int id_usuario, Usuario usuario, Authentication authentication) {
        // Buscar el usuario autenticado por su email
        String email = authentication.getName();
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()) {
            Usuario getUsuario = usuarioOptional.get();

            // Verificar que el usuario autenticado coincide con el usuario a actualizar
            if (getUsuario.getId() != id_usuario) {
                throw new IllegalArgumentException("No autorizado para actualizar este usuario");
            }

            // Buscar el usuario a actualizar por su id
            Optional<Usuario> usuarioToUpdateOpt = usuarioRepository.findById(id_usuario);
            if (usuarioToUpdateOpt.isPresent()) {
                Usuario usuarioToUpdate = usuarioToUpdateOpt.get();

                // Actualizar los campos del usuario
                usuarioToUpdate.setNombre(usuario.getNombre());
                usuarioToUpdate.setApellido(usuario.getApellido());
                usuarioToUpdate.setCelular(usuario.getCelular());
                usuarioToUpdate.setEmail(usuario.getEmail());

                usuarioRepository.save(usuarioToUpdate);
                return "Actualización exitosa";
            } else {
                throw new RuntimeException("El usuario no existe");
            }
        } else {
            throw new RuntimeException("Usuario autenticado no encontrado");
        }
    }

    // Eliminar un usuario
    public String deleteUsuario(int id_usuario, Authentication authentication) {
        String email = authentication.getName();
        Optional<Usuario> UsuarioEmailOpt = usuarioRepository.findByEmail(email);

        if (UsuarioEmailOpt.isPresent()) {

            Usuario getUsuario = UsuarioEmailOpt.get();
            if (getUsuario.getId() != id_usuario) {
                throw new IllegalArgumentException("No autorizado para eliminar este usuario");
            }

            usuarioRepository.deleteById(id_usuario);
            return "Eliminado exitosamente";

        } else {
            return "El usuario no existe";
        }
    }
}
