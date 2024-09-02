package org.example.backend_almenu.controller;

import org.example.backend_almenu.dto.producto.ProductoDTO;
import org.example.backend_almenu.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/v01/producto/")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    // Guardar un producto
    @PostMapping("new-producto")
    public ResponseEntity<?> createProducto(@RequestBody ProductoDTO productoDTO) {
        try {
            productoService.createProducto(productoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Categoria Guardada con exito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el producto");
        }
    }

}
