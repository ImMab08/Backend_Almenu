package org.example.backend_almenu.service;

import org.example.backend_almenu.dto.menu.MenuDTO;
import org.example.backend_almenu.dto.producto.ProductoDTO;
import org.example.backend_almenu.model.Categoria;
import org.example.backend_almenu.model.Subcategoria;
import org.example.backend_almenu.model.usuario.Usuario;
import org.example.backend_almenu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<MenuDTO> getMenuUsuario(int idCategoria, Authentication authentication) {
        String email = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        List<Categoria> categorias = usuario.getCategoria();
        List<Subcategoria> subcategorias = new ArrayList<>();

        for (Categoria categoria : categorias) {
            if (categoria.getId() == idCategoria) {
                subcategorias.addAll(categoria.getSubcategoria());
            }
        }

        List<MenuDTO> response = subcategorias.stream().map(subcategoria -> {
            MenuDTO dto = new MenuDTO();
            dto.setIdSubcategoria(subcategoria.getId());
            dto.setNombreSubcategoria(subcategoria.getNombre());

            List<ProductoDTO> productos = subcategoria.getProducto().stream().map(producto -> {
                ProductoDTO productoDto = new ProductoDTO();

                productoDto.setId_producto(producto.getId_producto());
                productoDto.setNombre(producto.getNombre());
                productoDto.setPrecio(producto.getPrecio());
                productoDto.setDescripcion(producto.getDescripcion());

                return productoDto;
            }).collect(Collectors.toList());

            dto.setProductosDTO(productos);
            return dto;

        }).collect(Collectors.toList());

        return response;
    }

}
