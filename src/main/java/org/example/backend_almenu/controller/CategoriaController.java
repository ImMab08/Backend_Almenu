package org.example.backend_almenu.controller;

import org.example.backend_almenu.dto.categoria.CategoriaDTO;
import org.example.backend_almenu.model.Categoria;
import org.example.backend_almenu.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/v01/categoria/")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    // Traer las categorias del usuario por su email
    @GetMapping("categorias/{email}")
    public List<Categoria> getCategoriasUsuarioByEmail(@PathVariable String email){
        return categoriaService.getCategoriaUsuariobyEmail(email);
    }

    // Crear categoria del usuario
    @PostMapping("create")
    public ResponseEntity<?> createCategoriaUsuario(@RequestBody CategoriaDTO categoriaDTO){
        try {
            categoriaService.createCategoriaUsuario(categoriaDTO);
            String menssage = "Categoria creada con exito";

            return new ResponseEntity<>(menssage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // Editar categoria
    @PutMapping("update")
    public Categoria updateCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.updateCategoriaUsuario(categoriaDTO.getId_categoria(), categoriaDTO);
    }

    // Eliminar Categoria
    @DeleteMapping("delete/{id}")
    public String deleteCategoria(@PathVariable("id") int id_categoria) {
        return categoriaService.deleteCategoriaUsuario(id_categoria);
    }
}
