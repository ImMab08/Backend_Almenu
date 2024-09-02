package org.example.backend_almenu.controller;

import org.example.backend_almenu.dto.categoria.CategoriaDTO;
import org.example.backend_almenu.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/v01/categorias/")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    // Traer las categorias del usuario
    @GetMapping("categoria")
    public List<CategoriaDTO> getCategoriasUsuarioById(@RequestParam("id_usuario") int id_usuario) {
        return categoriaService.getCategoriasUsuarioById(id_usuario);
    }

    // Guardar categoria
    @PostMapping("new-categoria")
    public ResponseEntity<?> saveCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        try {
            categoriaService.saveCategoriaDto(categoriaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Categoria guardad exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la categoría");
        }
    }

    // Editar categoria
    @PutMapping("update-categoria/{id}")
    public CategoriaDTO updateCategoria(@PathVariable("id") int id_categoria, @RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.updateCategoriaUsuarioById(id_categoria, categoriaDTO);
    }

    // Eliminar Categoria

    @DeleteMapping("delete-categoria/{id}")
    public String deleteCategoria(@PathVariable("id") int id_categoria) {
        return categoriaService.deleteCategoriaUsuarioById(id_categoria);
    }
}
