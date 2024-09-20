package org.example.backend_almenu.service;

import org.example.backend_almenu.dto.subcategoria.SubcategoriaDTO;
import org.example.backend_almenu.model.Categoria;
import org.example.backend_almenu.model.Subcategoria;
import org.example.backend_almenu.model.usuario.Usuario;
import org.example.backend_almenu.repository.SubcategoriaRepository;
import org.example.backend_almenu.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubcategoriaService {

    @Autowired
    SubcategoriaRepository subcategoriaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    // Traer las subcategorias con categorias del usuario.
    public List<SubcategoriaDTO> getAllSubcategorias(Authentication authentication) {
        String email = authentication.getName();

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<Categoria> categorias = usuario.getCategoria();
        List<Subcategoria> subcategorias = new ArrayList<>();

        // Recorre cada categoría y agrega sus subcategorías a la lista
        for (Categoria categoria : categorias) {
            subcategorias.addAll(categoria.getSubcategoria());
        }

        return subcategorias.stream().map(subcategoria -> {
            SubcategoriaDTO response = new SubcategoriaDTO();
            response.setId_subcategoria(subcategoria.getId());
            response.setNombre(subcategoria.getNombre());
            response.setDescripcion(subcategoria.getDescripcion());
            response.setId_categoria(subcategoria.getCategoria().getId());
            response.setNombreCategoria(subcategoria.getCategoria().getNombre());
            return response;
        }).collect(Collectors.toList());
    }

    // Crear una subcategoria para el usuario.
    public Subcategoria createSubcategoria(Subcategoria subcategoria, Authentication authentication) {
        // Obtener el usuario autenticado.
        String email = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        int idCategoria = subcategoria.getIdCategoria();
        Categoria categoria = usuario.getCategoria().stream()
                .filter(cat -> cat.getId().equals(idCategoria))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        // asociar la subcategoria con la categoria
        subcategoria.setCategoria(categoria);

        return subcategoriaRepository.save(subcategoria);
    }

    // Actualizar subcategorias del usuario.
    public Subcategoria updateSubcategoria(int id_subcategoria, SubcategoriaDTO subcategoriaDTO) {
        try {
            Subcategoria subcategoria = subcategoriaRepository.findById(id_subcategoria).get();
            subcategoria.setNombre(subcategoriaDTO.getNombre());
            subcategoria.setDescripcion(subcategoriaDTO.getDescripcion());

            return subcategoriaRepository.save(subcategoria);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Eliminar subcategorias del usuario.
    public String deleteSubcategoriaUsuario (int id_subcategoria) {
        Subcategoria subcategoria = subcategoriaRepository.findById(id_subcategoria)
                .orElseThrow(() -> new RuntimeException("Subcategoria no encontrada"));

        subcategoriaRepository.delete(subcategoria);
        return "Subcategoria eliminada";
    }

}
