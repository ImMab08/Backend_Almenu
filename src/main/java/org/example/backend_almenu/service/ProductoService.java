package org.example.backend_almenu.service;

import org.example.backend_almenu.dto.producto.ProductoDTO;
import org.example.backend_almenu.model.*;
import org.example.backend_almenu.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Traer todos los productos del usuario
    public List<ProductoDTO> getProductoUsuarioById(int id_usuario) {

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id_usuario);

        Usuario usuario = usuarioOptional.get();
        Restaurante restaurante = usuario.getRestaurante();

        List<ProductoDTO> productoDTO = restaurante.getProducto().stream()
                .map(producto -> {
                    ProductoDTO dto = new ProductoDTO();
                    dto.setNombre(producto.getNombre());
                    dto.setDescripcion(producto.getDescripcion());
                    dto.setPrecio(producto.getPrecio());
                    dto.setCantidad(producto.getCantidad());
                    dto.setImagen(producto.getImagen());
                    dto.setId_restaurante(restaurante.getId());
                    dto.setNombre_categoria(producto.getCategoria().getNombre());
                    dto.setId_categoria(producto.getCategoria().getId());
                    dto.setNombre_subcategoria(producto.getSubcategoria().getNombre());
                    dto.setId_subcategoria(producto.getSubcategoria().getId());
                    return dto;
                }).collect(Collectors.toList());

        return productoDTO;

    }


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

    // Actualizar Producto del usuario
    public ProductoDTO updateProductoUsuarioById(int id_producto, ProductoDTO productoDTO) {

        Producto producto = productoRepository.findProductoById(id_producto);
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(productoDTO.getId_categoria());
        Optional<Subcategoria> subcategoriaOptional = subcategoriaRepository.findById(productoDTO.getId_subcategoria());

        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setCantidad(productoDTO.getCantidad());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setImagen(productoDTO.getImagen());
        producto.setCategoria(categoriaOptional.get());
        producto.setSubcategoria(subcategoriaOptional.get());

        Producto updateProducto = productoRepository.save(producto);

        ProductoDTO dto = new ProductoDTO();
        dto.setNombre(updateProducto.getNombre());
        dto.setDescripcion(updateProducto.getDescripcion());
        dto.setCantidad(updateProducto.getCantidad());
        dto.setPrecio(updateProducto.getPrecio());
        dto.setImagen(updateProducto.getImagen());
        dto.setCantidad(updateProducto.getCantidad());
        dto.setId_restaurante(updateProducto.getRestaurante().getId());
        dto.setId_categoria(updateProducto.getCategoria().getId());
        dto.setId_subcategoria(updateProducto.getSubcategoria().getId());

        return  dto;
        
    }

    // Eliminar un producto del usuario.
    public String deleteProductoUsuarioById (int id_producto) {

        Optional<Producto> productoOptional = productoRepository.findById(id_producto);
        if (productoOptional != null) {
            productoRepository.deleteById(id_producto);
            return "Producto eliminado con exito";
        } else {
            return "Producto no encontrado";
        }

    }

}
