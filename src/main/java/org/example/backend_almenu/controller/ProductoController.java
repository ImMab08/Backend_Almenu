package org.example.backend_almenu.controller;

import org.example.backend_almenu.dto.producto.ProductoDTO;
import org.example.backend_almenu.model.Producto;
import org.example.backend_almenu.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/v01/producto/")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    // Traer los productos del usuario
    @GetMapping("usuario")
    public List<ProductoDTO> getProductosUsuario(Authentication authentication) {
        return productoService.getProductoUsuario(authentication);
    }

    // Guardar un producto
    @PostMapping("create")
    public ResponseEntity<?> createProducto(@RequestBody ProductoDTO productoDTO, Authentication authentication) {
        try {
            productoService.createProducto(productoDTO, authentication);
            return ResponseEntity.status(HttpStatus.CREATED).body("Categoria Guardada con exito");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el producto");
        }
    }

    // Actualizar un producto
    @PutMapping("update/{id_producto}")
    public Producto updateProducto(@PathVariable("id_producto") int id_producto, @RequestBody ProductoDTO productoDTO, Authentication authentication) {
        return productoService.updateProductoUsuario(id_producto, productoDTO, authentication);
    }

    // Eliminar un producto
    @DeleteMapping("delete/{id_producto}")
    public String deleteProducto(@PathVariable("id_producto") int id_producto, Authentication authentication) {
        return productoService.deleteProductoUsuarioById(id_producto, authentication);
    }

}
