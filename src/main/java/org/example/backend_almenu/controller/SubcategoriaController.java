package org.example.backend_almenu.controller;

import org.example.backend_almenu.dto.subcategoria.SubcategoriaDTO;
import org.example.backend_almenu.model.Subcategoria;
import org.example.backend_almenu.service.SubcategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/v01/subcategoria/")
public class SubcategoriaController {

    @Autowired
    SubcategoriaService subcategoriaService;

    // Traer a todas las subcategorias del usuario autenticado
    @GetMapping("subcategorias")
    public List<SubcategoriaDTO> getSubcategorias(Authentication authentication) {
        return subcategoriaService.getAllSubcategorias(authentication);
    }

    // Guardar Subcategoria
    @PostMapping("create")
    public ResponseEntity<?> createSubcategoria(@RequestBody Subcategoria subcategoria, Authentication authentication) {
        try {
            Subcategoria nuevaSubcategoria = subcategoriaService.createSubcategoria(subcategoria, authentication);
            return new ResponseEntity<>(nuevaSubcategoria, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la subcategoria");
        }
    }

    // Editar una subcategorias
    @PutMapping("update/{id}")
    public Subcategoria updateSubcategoria(@PathVariable int id, @RequestBody SubcategoriaDTO subcategoriaDTO) {
        return subcategoriaService.updateSubcategoria(id, subcategoriaDTO);
    }

    // Eliminar una subcategoria
    @DeleteMapping("delete/{id}")
    public String deleteSubcategoria(@PathVariable("id") int id_subcategoria) {
        return subcategoriaService.deleteSubcategoriaUsuario(id_subcategoria);
    }

}
