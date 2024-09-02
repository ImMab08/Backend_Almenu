package org.example.backend_almenu.controller;


import org.example.backend_almenu.dto.subcategoria.SubcategoriaDTO;
import org.example.backend_almenu.service.SubcategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/v01/subcategorias/")
public class SubcategoriaController {

    @Autowired
    SubcategoriaService subcategoriaService;

    // Guardar Subcategoria
    @PostMapping("new-subcategoria")
    public ResponseEntity<?> createSubcategoria(@RequestBody SubcategoriaDTO subcategoriaDTO) {
        try {
            SubcategoriaDTO createSubcategoria = subcategoriaService.createSubcategoria(subcategoriaDTO);
            return new ResponseEntity<>(createSubcategoria, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar usuario");
        }
    }

}
