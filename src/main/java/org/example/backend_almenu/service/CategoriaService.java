package org.example.backend_almenu.service;

import org.example.backend_almenu.model.Categoria;
import org.example.backend_almenu.dto.categoria.CategoriaDTO;
import org.example.backend_almenu.model.Restaurante;
import org.example.backend_almenu.model.usuario.Usuario;
import org.example.backend_almenu.repository.CategoriaRepository;

import org.example.backend_almenu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Categoria> categorias() {
        return categoriaRepository.findAll();
    }

    // Traer categorias del usuario por su email.
    public List<Categoria> getCategoriaUsuariobyEmail(String email) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        Usuario usuario = usuarioOpt.get();
        Restaurante restaurante = usuario.getRestaurante();
        if (restaurante == null) {
            throw new RuntimeException("El usuario no tiene un restaurante asociado");
        }

        List<Categoria> categorias = restaurante.getCategoria();

        return categorias;
    }

    // Crear una categoria para el usuario.
    public Categoria createCategoriaUsuario(CategoriaDTO categoriaDTO) {
        try {
            Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(categoriaDTO.getEmail());
            if (usuarioOpt.isEmpty()) {
                throw new RuntimeException("Usuario no encontrado");
            }

            Usuario usuario = usuarioOpt.get();
            Restaurante restaurante = usuario.getRestaurante();
            if (restaurante == null) {
                throw new RuntimeException("El usuario no tiene un restaurante asociado");
            }

            Categoria categoria = new Categoria();
            categoria.setNombre(categoriaDTO.getNombre());
            categoria.setDescripcion(categoriaDTO.getDescripcion());
            categoria.setRestaurante(restaurante);

            return categoriaRepository.save(categoria);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Actualizar categoria del usuario
    public Categoria updateCategoriaUsuario(int id_categoria, CategoriaDTO categoriaDTO) {
        try {
            Categoria categoria = categoriaRepository.findById(id_categoria).get();
            categoria.setNombre(categoriaDTO.getNombre());
            categoria.setDescripcion(categoriaDTO.getDescripcion());

            return categoriaRepository.save(categoria);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Eliminar categoria del usaurio
    public String deleteCategoriaUsuario(int id_categoria) {
        Categoria categoria = categoriaRepository.findById(id_categoria).get();
        if (categoria != null) {
            categoriaRepository.delete(categoria);
            return "Categoria eliminada";
        } else {
            return "Categoria no encontrada";
        }
    }

}
