package org.example.backend_almenu.controller;


import org.example.backend_almenu.dto.subcategoria.SubcategoriaDTO;
import org.example.backend_almenu.model.Categoria;
import org.example.backend_almenu.model.Subcategoria;
import org.example.backend_almenu.service.SubcategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/v01/subcategoria/")
public class SubcategoriaController {

    @Autowired
    SubcategoriaService subcategoriaService;

    // Traer a todas las subcategorias de un usuario
    @GetMapping("subcategorias/{email}")
    public List<Subcategoria> getSubcategoriasUsuarioById(@PathVariable String email) {
        return subcategoriaService.getSubcategoriaUsuario(email);
    }

    //Traer todas las subcategorias de una categoria del usuario
    @GetMapping("subcategoria/{email}")
    public List<Subcategoria> getSubcategoriasUsuarioByEmail(@PathVariable String email, @RequestParam String categoria) {
        List<Subcategoria> subcategorias = subcategoriaService.getSubcategoriaPorCategoria(email, categoria);
        return subcategorias;
    }

    // Guardar Subcategoria
    @PostMapping("create")
    public ResponseEntity<?> createSubcategoria(@RequestBody SubcategoriaDTO subcategoriaDTO) {
        try {
            subcategoriaService.createSubcategoria(subcategoriaDTO);
            return new ResponseEntity<>("Subcatedgoria creada con exito", HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar usuario");
        }
    }

    // Editar una subcategorias
    @PutMapping("update/{id}")
    public Subcategoria updateSubcategoria(@PathVariable int id, @RequestBody SubcategoriaDTO subcategoriaDTO) {
        return subcategoriaService.updateSubcategoria(id, subcategoriaDTO);
    }

//    @PutMapping("update/{email}/{id}")
//    public Subcategoria updateSubcategoria(@PathVariable String email, @PathVariable int id, @RequestBody SubcategoriaDTO subcategoriaDTO) {
//        return subcategoriaService.updateSubcategoria(email, id, subcategoriaDTO);
//    }

    // Eliminar una subcategoria
    @DeleteMapping("delete/{id_subcategoria}")
    public String deleteSubcategoria(@PathVariable int id_subcategoria) {
        return subcategoriaService.deleteSubcategoriaById(id_subcategoria);
    }

}
