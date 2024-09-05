package org.example.backend_almenu.controller;

import org.example.backend_almenu.dto.producto.ProductoDTO;
import org.example.backend_almenu.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/v01/producto/")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    // Traer los productos del usuario
    @GetMapping("productos/{email}")
    public List<ProductoDTO> getProductosUsuarioById(@PathVariable String email) {
        return productoService.getProductoUsuario(email);
    }

    // Guardar un producto
    @PostMapping("create")
    public ResponseEntity<?> createProducto(@RequestBody ProductoDTO productoDTO) {
        try {
            productoService.createProducto(productoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Categoria Guardada con exito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el producto");
        }
    }

    // Actualizar un producto
    @PutMapping("update/{id}")
    public ResponseEntity<?> updateProducto(@PathVariable("id") int id_producto, @RequestBody ProductoDTO productoDTO) {
        try {
            productoService.updateProductoUsuario(id_producto, productoDTO);
            return new ResponseEntity("Producto actualizado con exito", HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar el producto");
        }
    }

    // Eliminar un producto
    @DeleteMapping("delete/{id}")
    public String deleteProducto(@PathVariable("id") int id_producto) {
        return productoService.deleteProductoUsuarioById(id_producto);
    }

}
