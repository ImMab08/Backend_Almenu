package org.example.backend_almenu.service;

import org.example.backend_almenu.model.Categoria;
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

    // Traer todas las categorias del usuario.
    public List<Categoria> getCategoriaUsuario(Authentication authentication) {
        //  Enlistar categorías del usuario authenticado.
        String email = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Categoria> categorias = usuario.getCategoria();
        return categorias;
    }

    // Crear una categoria para el usuario.
    public Categoria createCategoriaUsuario(Categoria createCategoria, Authentication authentication) {
        String email = authentication.getName();
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            createCategoria.setUsuario(usuario);
            return categoriaRepository.save(createCategoria);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    // Actualizar categoria del usuario
    public String updateCategoriaUsuario(int id_categoria, Categoria updateCategoria, Authentication authentication) {
        String email = authentication.getName();
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // Buscar la categoria en la lista de las categorias del usuario.
            Categoria categoria =  usuario.getCategoria()
                    .stream() // Iterar entre las categoria.
                    .filter(idCategoria -> idCategoria.getId() == id_categoria) // Buscamos la categoria con el ID enviado.
                    .findFirst() // Recuperar la primera coincidencia.
                    .orElseThrow(() -> new RuntimeException("Categoria no encontrada o no pertenece a este usuario")); // Lanzamos una excepción en caso de error.

            // Actualizar campos de la cateogira.
            categoria.setNombre(updateCategoria.getNombre());
            categoria.setDescripcion(updateCategoria.getDescripcion());

            // Guardar la categoria actualizada en la db.
            categoriaRepository.save(categoria);
            return "Categoria actualizada exitosamente.";
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    // Eliminar categoria del usuario
    public String deleteCategoriaUsuario(int id_categoria, Authentication authentication) {
        String email = authentication.getName();
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // Buscar la categoria en la lista de las categorias del usuario.
            Categoria categoria =  usuario.getCategoria()
                    .stream() // Iterar entre las categoria.
                    .filter(idCategoria -> idCategoria.getId() == id_categoria) // Buscamos la categoria con el ID enviado.
                    .findFirst() // Recuperar la primera coincidencia.
                    .orElseThrow(() -> new RuntimeException("Categoria no encontrada o no pertenece a este usuario")); // Lanzamos una excepción en caso de error.

            // Eliminar la categoria de la lista del usuario.
            usuario.getCategoria().remove(categoria);

            // Eliminar la categoria de la bd.
            categoriaRepository.delete(categoria);
            return "Categoria eliminado exitosamente.";
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

}