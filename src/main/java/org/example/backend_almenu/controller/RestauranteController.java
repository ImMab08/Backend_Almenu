package org.example.backend_almenu.controller;


import org.example.backend_almenu.model.Restaurante;
import org.example.backend_almenu.model.Usuario;
import org.example.backend_almenu.repository.UsuarioRepository;
import org.example.backend_almenu.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/v01/restaurante/")
public class RestauranteController {

    @Autowired
    RestauranteService restauranteService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Obtener todos los restaurantes
    @GetMapping("restaurantes")
    public List<Restaurante> getRestaurantes() {
        return restauranteService.restaurante();
    }

    // Obtener restaurante por ID
    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> getRestauranteById(@PathVariable Integer id) {
        Restaurante restaurante = restauranteService.getRestauranteById(id);
        if (restaurante != null) {
            return new ResponseEntity<>(restaurante, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Obtener la información de configuración del restaurante del usuario
    @GetMapping("/settings/{id}")
    public ResponseEntity<Restaurante> getSettingsInfoRestaurante(@PathVariable Integer id) {
        Restaurante restaurante = restauranteService.getSettingsInfoRestaurante(id);
        if (restaurante != null) {
            return new ResponseEntity<>(restaurante, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Guardar la información del restaurante del usuario
    @PostMapping("/new-restaurante")
    public ResponseEntity<?> saveRestaurante(@RequestParam("id_usuario") int id_usuario, @RequestBody Restaurante restaurante) {

        // Recuperar el objeto Usuario usando el id_usuario
        Usuario usuario = usuarioRepository.findById(id_usuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id_usuario));

        // Configurar el restaurante con el usuario recuperado
        restaurante.setUsuario(usuario);

        // Llamar al servicio para guardar el restaurante
        String result = restauranteService.saveRestaurante(restaurante);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // Actualizar la información del restaurante
    @PutMapping("update-user")
    public String updateRestaurante(@RequestBody Restaurante restaurante) {
        String mensaje = restauranteService.updateRestaurante(restaurante);
        return mensaje;
    }

}
