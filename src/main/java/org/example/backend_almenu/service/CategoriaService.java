package org.example.backend_almenu.service;

import jakarta.transaction.Transactional;
import org.example.backend_almenu.model.Categoria;
import org.example.backend_almenu.dto.categoria.CategoriaDTO;
import org.example.backend_almenu.model.Restaurante;
import org.example.backend_almenu.model.usuario.Usuario;
import org.example.backend_almenu.repository.CategoriaRepository;

import org.example.backend_almenu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    // Traer categorias del usuario por su email.
    public List<Categoria> getCategoriaUsuario(Authentication authentication) {
        String email = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Verificar si el usuario tiene un restaurante asociado.
        Restaurante restaurante = usuario.getRestaurante();
        if (restaurante == null) {
            throw new RuntimeException("El usuario no tiene un restaurante asociado");
        }

        List<Categoria> categorias = restaurante.getCategoria();
        return categorias;
    }

    // Crear una categoria para el usuario.
    public Categoria createCategoriaUsuario(String email, Categoria categoria) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Restaurante restaurante = usuario.getRestaurante();
        if (restaurante == null) {
            throw new RuntimeException("El usuario no tiene un restaurante asociado");
        }

        categoria.setRestaurante(restaurante);

        return categoriaRepository.save(categoria);
    }

    // Actualizar categoria del usuario
    public Categoria updateCategoriaUsuario(int id_categoria, Categoria categoria) {
        Categoria updateCategoria = categoriaRepository.findById(id_categoria)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        updateCategoria.setNombre(categoria.getNombre());
        updateCategoria.setDescripcion(categoria.getDescripcion());

        return categoriaRepository.save(updateCategoria);
    }

    // Eliminar categoria del usuario
    public String deleteCategoriaUsuario(int id_categoria) {
        Categoria categoria = categoriaRepository.findById(id_categoria)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        categoriaRepository.delete(categoria);
        return "Categoría eliminada";
    }

}
