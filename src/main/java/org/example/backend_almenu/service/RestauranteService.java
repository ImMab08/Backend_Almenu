package org.example.backend_almenu.service;

import org.example.backend_almenu.model.Restaurante;
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

    public Restaurante getRestauranteById(Integer id) {

        return restauranteRepository.findRestauranteById(id);
    }

    // Traer la información del resturante del usuario.
    public Restaurante getSettingsInfoRestaurante(Integer id) {
        Restaurante restaurante = restauranteRepository.findRestauranteById(id);
        return restaurante;
    }

    // Guardar información del restaurante del usuario.
    public String saveRestaurante(Restaurante restaurante) {
        try {
            restauranteRepository.save(restaurante);
            return  "Información del restaurante guardada exitosamente.";
        } catch (Exception e) {
            return "Error al guardar la información del restaurante. ERROR: " + e.getMessage();
        }
    }

}
