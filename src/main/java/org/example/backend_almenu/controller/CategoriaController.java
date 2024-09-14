package org.example.backend_almenu.controller;

import org.example.backend_almenu.model.Categoria;
import org.example.backend_almenu.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/v01/categoria/")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    // Traer las categorias del usuario
    @GetMapping("usuario")
    public List<Categoria> getCategoriasUsuarioByEmail(Authentication authentication) {
        return categoriaService.getCategoriaUsuario(authentication);
    }

    // Crear categoria del usuario
    @PostMapping("/create")
    public ResponseEntity<?> createCategoriaUsuario(@RequestBody Categoria categoria, Authentication authentication) {
        try {
            String email = authentication.getName();
            Categoria nuevaCategoria = categoriaService.createCategoriaUsuario(email, categoria);
            return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Editar categoria
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategoria(@PathVariable("id") int id_categoria, @RequestBody Categoria categoria) {
        try {
            Categoria updatedCategoria = categoriaService.updateCategoriaUsuario(id_categoria, categoria);
            return new ResponseEntity<>(updatedCategoria, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Eliminar Categoria
    @DeleteMapping("delete/{id}")
    public String deleteCategoria(@PathVariable("id") int id_categoria) {
        return categoriaService.deleteCategoriaUsuario(id_categoria);
    }
}
