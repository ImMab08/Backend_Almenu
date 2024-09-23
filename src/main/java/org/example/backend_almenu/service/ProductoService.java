package org.example.backend_almenu.service;

import org.example.backend_almenu.dto.producto.ProductoDTO;
import org.example.backend_almenu.model.*;
import org.example.backend_almenu.model.usuario.Usuario;
import org.example.backend_almenu.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    SubcategoriaRepository subcategoriaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Traer todos los productos del usuario

    public List<ProductoDTO> getProductoUsuario(Authentication authentication) {
        String email = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Categoria> categorias = usuario.getCategoria();
        List<ProductoDTO> productosDTO = new ArrayList<>();

        // Recorremos todas las categorias del usuario.
        for (Categoria categoria : categorias) {
            // Obtenemos las subcategorias de cada categoria.
            List<Subcategoria> subcategorias = categoria.getSubcategoria();

            // Recorremos todas las subcategorias del usuario.
            for (Subcategoria subcategoria : subcategorias) {
                // Obtenemos los productos de cada subcategoria.
                List<Producto> productos = subcategoria.getProducto();

                // Convertimos y agregamos cada producto a ProductoDTO en la misma iteración
                for (Producto producto : productos) {
                    productosDTO.add(new ProductoDTO(
                            producto.getId_producto(),
                            producto.getNombre(),
                            producto.getDescripcion(),
                            producto.getPrecio(),
                            producto.getCantidad(),
                            producto.getImagen(),
                            categoria.getId(),
                            categoria.getNombre(),
                            subcategoria.getId(),
                            subcategoria.getNombre()
                    ));
                }
            }
        }

        return productosDTO;
    }


    // Guardar un producto
    public Producto createProducto(ProductoDTO productoDTO, Authentication authentication) {
        // Obtener el email del usuario autenticado
        String email = authentication.getName();

        // Buscar al usuario por su email
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Buscar la categoría seleccionada por el usuario
        int idCategoria = productoDTO.getIdCategoria();
        Categoria categoria = usuario.getCategoria().stream()
                .filter(cat -> cat.getId().equals(idCategoria))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        // Buscar la subcategoría seleccionada dentro de la categoría
        int idSubcategoria = productoDTO.getIdSubcategoria();
        Subcategoria subcategoria = categoria.getSubcategoria().stream()
                .filter(subcat -> subcat.getId().equals(idSubcategoria))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Subcategoria no encontrada"));

        // Crear un nuevo objeto Producto a partir del ProductoDTO
        Producto producto = new Producto();
        producto.setId_producto(producto.getId_producto());
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setCantidad(productoDTO.getCantidad());
        producto.setImagen(productoDTO.getImagen());
        producto.setUsuario(usuario);
        producto.setCategoria(categoria);  // Relacionar con la categoría
        producto.setSubcategoria(subcategoria);  // Relacionar con la subcategoría

        // Guardar el producto en el repositorio
        return productoRepository.save(producto);
    }

    // Actualizar Producto del usuario
    public Producto updateProductoUsuario(int id_producto, ProductoDTO productoDTO, Authentication authentication) {
        String email = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Producto producto = productoRepository.findProductoById(id_producto);

        if (!producto.getUsuario().equals(usuario)) {
            throw new RuntimeException("No tienes permisos para actualizar este producto");
        }

        Categoria categoria = categoriaRepository.findById(productoDTO.getIdCategoria())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrado"));
        Subcategoria subcategoria = subcategoriaRepository.findById(productoDTO.getIdSubcategoria())
                .orElseThrow(() -> new RuntimeException("Subcategoria no encontrado"));

        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setCantidad(productoDTO.getCantidad());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setImagen(productoDTO.getImagen());
        producto.setCategoria(categoria);
        producto.setSubcategoria(subcategoria);

        return productoRepository.save(producto);

    }

    // Eliminar un producto del usuario.
    public String deleteProductoUsuarioById (int id_producto, Authentication authentication) {
        String email = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Producto producto = productoRepository.findById(id_producto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (!producto.getUsuario().equals(usuario)) {
            throw new RuntimeException("No tienes permiso para eliminar este producto");
        }

        productoRepository.delete(producto);
        return "Producto eliminado exitosamente";
    }

}
