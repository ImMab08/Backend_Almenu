package org.example.backend_almenu.controller;

import org.example.backend_almenu.dto.producto.ProductoDTO;
import org.example.backend_almenu.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/v01/producto/")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    // Traer los productos del usuario
    @GetMapping("productos")
    public List<ProductoDTO> getProductosUsuarioById(@RequestParam("id_usuario") int id_usuario) {
        return productoService.getProductoUsuarioById(id_usuario);
    }

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

    // Actualizar un producto
    @PutMapping("update-categoria/{id}")
    public ProductoDTO updateProducto(@PathVariable("id") int id_producto, @RequestBody ProductoDTO productoDTO) {
        return productoService.updateProductoUsuarioById(id_producto, productoDTO);
    }


    // Eliminar un producto
    @DeleteMapping("delete-producto/{id}")
    public String deleteProducto(@PathVariable("id") int id_producto) {
        return productoService.deleteProductoUsuarioById(id_producto);
    }

}
