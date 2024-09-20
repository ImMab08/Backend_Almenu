package org.example.backend_almenu.controller;

import org.example.backend_almenu.model.Restaurante;
import org.example.backend_almenu.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/v01/restaurante/")
public class RestauranteController {

    @Autowired
    RestauranteService restauranteService;

    // Obtener todos los restaurantes
    @GetMapping("restaurantes")
    public List<Restaurante> getRestaurantes() {
        return restauranteService.restaurante();
    }

    // Obtener el restaurante del usuario con su Email.
    @GetMapping("usuario")
    public Restaurante getRestauranteUsuarioByEmail(Authentication authentication) {
        return restauranteService.getRestauranteUsuario(authentication);
    }
    // Guardar la información del restaurante del usuario
    @PostMapping("create")
    public ResponseEntity<?> createRestaurante(@RequestBody Restaurante createRestaurante, Authentication authentication) {
        try {
            Restaurante newRestaurante = restauranteService.createRestaurante(createRestaurante, authentication);
            return new ResponseEntity<>(newRestaurante, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el restaurante");
        }
    }

    // Actualizar la información del restaurante
    @PutMapping("update/{id_restaurante}")
    public ResponseEntity<?> updateRestaurante(@PathVariable("id_restaurante") int id_restaurante, @RequestBody Restaurante updateRestaurante, Authentication authentication) {
        try {
            String restauranteActualizado = restauranteService.updateRestaurante(id_restaurante, updateRestaurante, authentication);
            return new ResponseEntity<>(restauranteActualizado, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el restaurante");
        }
    }

}
