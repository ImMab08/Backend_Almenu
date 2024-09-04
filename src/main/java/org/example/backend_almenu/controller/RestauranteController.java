package org.example.backend_almenu.controller;


import org.example.backend_almenu.model.Restaurante;
import org.example.backend_almenu.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
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
    @GetMapping("usuario/{email}")
    public Restaurante getRestauranteUsuarioByEmail(@PathVariable String email) {
        return restauranteService.getRestauranteUsuarioByEmail(email);
    }

    // Guardar la información del restaurante del usuario
    @PostMapping("create/{email}")
    public String createRestaurante(@PathVariable String email, @RequestBody Restaurante restaurante) {
        String mensaje = restauranteService.createRestaurante(email, restaurante);
        return mensaje;
    }

    // Actualizar la información del restaurante
    @PutMapping("update/{email}")
    public String updateRestaurante(@PathVariable String email, @RequestBody Restaurante restaurante) {
        try {
            String mensaje = restauranteService.updateRestaurante(email, restaurante);
            return mensaje;
        } catch (Exception e) {
            return "error al actualizar el restarurnate" + e.getMessage();
        }
    }

}
