package org.example.backend_almenu.service;

import org.example.backend_almenu.dto.producto.ProductoDTO;
import org.example.backend_almenu.model.Categoria;
import org.example.backend_almenu.model.Producto;
import org.example.backend_almenu.model.Restaurante;
import org.example.backend_almenu.model.Subcategoria;
import org.example.backend_almenu.repository.CategoriaRepository;
import org.example.backend_almenu.repository.ProductoRepository;
import org.example.backend_almenu.repository.RestauranteRepository;
import org.example.backend_almenu.repository.SubcategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    RestauranteRepository restauranteRepository;
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    SubcategoriaRepository subcategoriaRepository;

    // Guardar un producto
    public ProductoDTO createProducto(ProductoDTO productoDTO) {
        Restaurante restaurante = restauranteRepository.findById(productoDTO.getId_restaurante())
                .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));

        Categoria categoria = categoriaRepository.findById(productoDTO.getId_categoria())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrado"));

        Subcategoria subcategoria = subcategoriaRepository.findById(productoDTO.getId_subcategoria())
                .orElseThrow(() -> new RuntimeException("Subcategoria no encontrado"));

        Producto producto = new Producto();

        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setImagen(productoDTO.getImagen());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setCantidad(productoDTO.getCantidad());
        producto.setRestaurante(restaurante);
        producto.setCategoria(categoria);
        producto.setSubcategoria(subcategoria);

        Producto createProducto = productoRepository.save(producto);

        ProductoDTO dto = new ProductoDTO();

        dto.setNombre(createProducto.getNombre());
        dto.setDescripcion(createProducto.getDescripcion());
        dto.setImagen(createProducto.getImagen());
        dto.setPrecio(createProducto.getPrecio());
        dto.setCantidad(createProducto.getCantidad());
        dto.setId_restaurante(createProducto.getRestaurante().getId());
        dto.setId_categoria(createProducto.getCategoria().getId());
        dto.setId_subcategoria(createProducto.getSubcategoria().getId());

        return  dto;

    }

}
