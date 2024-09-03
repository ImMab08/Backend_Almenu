package org.example.backend_almenu.controller;


import org.example.backend_almenu.dto.subcategoria.SubcategoriaDTO;
import org.example.backend_almenu.service.SubcategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/v01/subcategorias/")
public class SubcategoriaController {

    @Autowired
    SubcategoriaService subcategoriaService;

    // Traer a todas las subcategorias de un usuario
    @GetMapping("subcategoria")
    public List<SubcategoriaDTO> getSubcategoriasUsuarioById(@RequestParam("id_usuario") int id_usuario){
        return subcategoriaService.getSubcategoriaUsuarioById(id_usuario);
    }

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

    // Editar una subcategorias
    @PutMapping("update-subcategoria/{id}")
    public SubcategoriaDTO updateSubcategoria(@PathVariable("id") int id_subcategoria, @RequestBody SubcategoriaDTO subcategoriaDTO) {
        return subcategoriaService.updateSubcategoriaUsuarioById(id_subcategoria, subcategoriaDTO);
    }

    // Eliminar una subcategoria
    @DeleteMapping("delete-subcategoria/{id}")
    public String deleteSubcategoria(@PathVariable("id") int id_subcategoria) {
        return subcategoriaService.deleteSubcategoriaById(id_subcategoria);
    }

}
