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
    public List<ProductoDTO> getProductoUsuario(String email) {

        Usuario usuario = usuarioRepository.getEmail(email);
        Restaurante restaurante = usuario.getRestaurante();

        List<ProductoDTO> productoDTO = restaurante.getProducto().stream()
                .map(producto -> {
                    ProductoDTO dto = new ProductoDTO();
                    dto.setId_producto(producto.getId_producto());
                    dto.setNombre(producto.getNombre());
                    dto.setDescripcion(producto.getDescripcion());
                    dto.setPrecio(producto.getPrecio());
                    dto.setCantidad(producto.getCantidad());
                    dto.setImagen(producto.getImagen());
                    dto.setId_categoria(producto.getCategoria().getId());
                    dto.setNombreCategoria(producto.getCategoria().getNombre());
                    dto.setId_subcategoria(producto.getSubcategoria().getId());
                    dto.setNombreSubcategoria(producto.getSubcategoria().getNombre());
                    return dto;
                }).collect(Collectors.toList());

        return productoDTO;

    }

    // Guardar un producto
    public Producto createProducto(ProductoDTO productoDTO) {

        try {
            Usuario usuario = usuarioRepository.getEmail(productoDTO.getEmail());
            if (usuario == null) {
                throw new Exception("No existe el usuario con ese email");
            }

            Restaurante restaurante = usuario.getRestaurante();
            if (restaurante == null) {
                throw new Exception("No existe el restaurante con ese email");
            }

            Categoria categoria = categoriaRepository.findById(productoDTO.getId_categoria())
                    .orElseThrow(() -> new RuntimeException("Categoria no encontrado"));

            Subcategoria subcategoria = subcategoriaRepository.findById(productoDTO.getId_subcategoria())
                    .orElseThrow(() -> new RuntimeException("Subcategoria no encontrado"));

            Producto producto = new Producto();

            // Crear producto
            producto.setNombre(productoDTO.getNombre());
            producto.setDescripcion(productoDTO.getDescripcion());
            producto.setImagen(productoDTO.getImagen());
            producto.setPrecio(productoDTO.getPrecio());
            producto.setCantidad(productoDTO.getCantidad());

            // Asignar producto
            producto.setRestaurante(restaurante);
            producto.setCategoria(categoria);
            producto.setSubcategoria(subcategoria);

            return productoRepository.save(producto);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el producto");
        }

    }

    // Actualizar Producto del usuario
    public ProductoDTO updateProductoUsuario(int id_producto, ProductoDTO productoDTO) {

        Producto producto = productoRepository.findProductoById(id_producto);
        Categoria categoria = categoriaRepository.findById(productoDTO.getId_categoria())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrado"));
        Subcategoria subcategoria = subcategoriaRepository.findById(productoDTO.getId_subcategoria())
                .orElseThrow(() -> new RuntimeException("Subcategoria no encontrado"));

        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setCantidad(productoDTO.getCantidad());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setImagen(productoDTO.getImagen());
        producto.setCategoria(categoria);
        producto.setSubcategoria(subcategoria);

        Producto updateProducto = productoRepository.save(producto);

        ProductoDTO dto = new ProductoDTO();
        dto.setNombre(updateProducto.getNombre());
        dto.setDescripcion(updateProducto.getDescripcion());
        dto.setCantidad(updateProducto.getCantidad());
        dto.setPrecio(updateProducto.getPrecio());
        dto.setImagen(updateProducto.getImagen());
        dto.setCantidad(updateProducto.getCantidad());
        dto.setId_categoria(updateProducto.getCategoria().getId());
        dto.setId_subcategoria(updateProducto.getSubcategoria().getId());

        return dto;

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
