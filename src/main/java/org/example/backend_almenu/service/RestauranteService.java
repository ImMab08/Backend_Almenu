package org.example.backend_almenu.service;

import org.example.backend_almenu.model.Restaurante;
import org.example.backend_almenu.model.Usuario;
import org.example.backend_almenu.repository.RestauranteRepository;
import org.example.backend_almenu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    RestauranteRepository restauranteRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Restaurante> restaurante() {
        return restauranteRepository.findAll();
    }

    // Traer el restaurante del usuario con su Email.
    public Restaurante getRestauranteUsuarioByEmail(String email) {
        return usuarioRepository.getEmail(email).getRestaurante();
    }

    // Crear el restaurante del usuario.
    public String createRestaurante(String email, Restaurante restaurante) {
        try {
            // Traemos al usuario con su email
            Usuario usuario = usuarioRepository.getEmail(email);

            // Verificar si ya el usuario tiene un restaurante
            if (usuario.getRestaurante() != null) {
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

    public String saveRestaurante(Restaurante restaurante) {
        try {
            restauranteRepository.save(restaurante);
            return  "Información del restaurante guardada exitosamente.";
        } catch (Exception e) {
            return "Error al guardar la información del restaurante. ERROR: " + e.getMessage();
        }
    }

    // Actualizar información del restaurante del usuario.
    public String updateRestaurante(String email, Restaurante restaurante) {
        Usuario usuario = usuarioRepository.getEmail(email);
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
