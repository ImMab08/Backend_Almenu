package org.example.backend_almenu.service;

import org.example.backend_almenu.model.Restaurante;
import org.example.backend_almenu.model.usuario.Usuario;
import org.example.backend_almenu.repository.RestauranteRepository;
import org.example.backend_almenu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    RestauranteRepository restauranteRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Restaurante> restaurante() {
        return restauranteRepository.findAll();
    }

    // Traer el restaurante del usuario al settings.
    public Restaurante getRestauranteUsuario(Authentication authentication) {
        String email = authentication.getName();

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

        // Verificar si el usuario existe
        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado con el email: " + email);
        }

        Usuario usuario = usuarioOpt.get();

        // Verificar si el usuario tiene un restaurante asociado
        if (usuario.getRestaurante() == null) {
            throw new RuntimeException("El usuario no tiene un restaurante asociado.");
        }

        return usuario.getRestaurante();
    }

    // Crear el restaurante del usuario.
    public String createRestaurante(String email, Restaurante restaurante) {
        try {
            // Traemos al usuario con su email
            Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
            Usuario usuario = usuarioOpt.get();

            // Verificar si ya el usuario tiene un restaurante
            if (!usuarioOpt.isPresent()) {
                return "El usuario ya tiene un restaurante creadod.";
            }

            // Asociar la información
            restaurante.setUsuario(usuario);

            // Guardar la información
            restauranteRepository.save(restaurante);

            return "Restaurante creado con exito";
        } catch (Exception e) {
            return "Error al guardar la información del restaurante. ERROR: " + e.getMessage();
        }
    }

    // Actualizar información del restaurante del usuario.
    public String updateRestaurante(String email, Restaurante restaurante) {

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        Usuario usuario = usuarioOpt.get();
        if (usuario == null) {
            return "El usuario no existe";
        }

        Restaurante updateRestaurante = usuario.getRestaurante();
        if (updateRestaurante != null) {
            updateRestaurante.setNombre(restaurante.getNombre());
            updateRestaurante.setCiudad(restaurante.getCiudad());
            updateRestaurante.setDireccion(restaurante.getDireccion());
            updateRestaurante.setLogo(restaurante.getLogo());

            restauranteRepository.save(updateRestaurante);
            return "Restaurante actualizado";
        } else {
            return "El usuario no tiene un restaurante asociado";
        }
    }

}
