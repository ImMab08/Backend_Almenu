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
    public Restaurante createRestaurante(Restaurante createRestaurante, Authentication authentication) {
        String email = authentication.getName();
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            if (usuario.getRestaurante() != null) {
                throw new RuntimeException("El usuario ya tiene un restaurante asociado.");
            }

            // Asociar el restaurante al usuario.
            createRestaurante.setUsuario(usuario);

            // Guardar el restaurante en la base de datos.
            return restauranteRepository.save(createRestaurante);
        } else  {
            throw new RuntimeException("El usuario no encontrado.");
        }
    }

    // Actualizar información del restaurante del usuario.
    public String updateRestaurante(int id_restaurante, Restaurante updateRestaurante, Authentication authentication) {
        String email = authentication.getName();
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()) {

            Usuario usuario = usuarioOptional.get();
            Restaurante restaurante = usuario.getRestaurante();
            if (restaurante == null || restaurante.getId() != id_restaurante) {
                throw new RuntimeException("Restaurante no encontrado o no pertenece a este usuario.");
            }

            restaurante.setNombre(updateRestaurante.getNombre());
            restaurante.setCiudad(updateRestaurante.getCiudad());
            restaurante.setDireccion(updateRestaurante.getDireccion());
            restaurante.setLogo(updateRestaurante.getLogo());

            restauranteRepository.save(restaurante);
            return "Restaurante actualizado con éxito.";
        } else {
            throw new RuntimeException("Usuario no encontrado con el email: " + email);
        }

    }

    // Eliminar la información del restaurante.

}
