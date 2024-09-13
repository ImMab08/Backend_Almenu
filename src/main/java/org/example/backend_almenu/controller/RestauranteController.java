package org.example.backend_almenu.controller;


import org.apache.coyote.Response;
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
    public ResponseEntity<?> createRestaurante(@RequestBody Restaurante restaurante, Authentication authentication) {
        try {
            String email = authentication.getName();
            String mensaje = restauranteService.createRestaurante(email, restaurante);
            return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el restaurante");
        }
    }

    // Actualizar la información del restaurante
    @PutMapping("update/{id}")
    public ResponseEntity<?> updateRestaurante(@RequestBody Restaurante restaurante, Authentication authentication) {
        try {
            String email = authentication.getName();
            String mensaje = restauranteService.updateRestaurante(email, restaurante);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el restaurante");
        }
    }

}
