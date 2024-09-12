package org.example.backend_almenu.service;

import org.example.backend_almenu.dto.subcategoria.SubcategoriaDTO;
import org.example.backend_almenu.model.Categoria;
import org.example.backend_almenu.model.Restaurante;
import org.example.backend_almenu.model.Subcategoria;
import org.example.backend_almenu.model.usuario.Usuario;
import org.example.backend_almenu.repository.CategoriaRepository;
import org.example.backend_almenu.repository.RestauranteRepository;
import org.example.backend_almenu.repository.SubcategoriaRepository;
import org.example.backend_almenu.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubcategoriaService {

    @Autowired
    SubcategoriaRepository subcategoriaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    RestauranteRepository restauranteRepository;
    @Autowired
    CategoriaRepository categoriaRepository;

    public List<Subcategoria> subcategorias(){
        return subcategoriaRepository.findAll();
    }

    // Traer subcategorias del usuario con su id.
    public List<Subcategoria> getSubcategoriaUsuario(String email) {

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        Usuario usuario = usuarioOpt.get();
        Restaurante restaurante = usuario.getRestaurante();
        if (restaurante == null) {
            throw new RuntimeException("Restaurante no encontrado");
        }

        List<Categoria> categorias = restaurante.getCategoria();
        if (categorias == null || categorias.isEmpty()) {
            throw new RuntimeException("Categoria no encontrado");
        }

        List<Subcategoria> subcategorias = new ArrayList<>();
        for (Categoria categoria : categorias) {
            if (categoria.getSubcategoria() != null) {
                subcategorias.addAll(categoria.getSubcategoria());
            }
        }

        return subcategorias;
    }

    // Traer subcategorias dependiendo de la categoria del usuario.
    public List<Subcategoria> getSubcategoriaPorCategoria(String email, String nombreCategoria) {

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        Usuario usuario = usuarioOpt.get();
        Restaurante restaurante = usuario.getRestaurante();
        if (restaurante == null) {
            throw new RuntimeException("Restaurante no encontrado");
        }

        Categoria categoria = restaurante.getCategoria().stream()
                .filter(c -> c.getNombre().equalsIgnoreCase(nombreCategoria))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        return categoria.getSubcategoria();
    }

    // Guardar subcategorias del usuario.
    public Subcategoria createSubcategoria(SubcategoriaDTO subcategoriaDTO)  {
        try {
            Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(subcategoriaDTO.getEmail());
            if (usuarioOpt.isEmpty()) {
                throw new RuntimeException("Usuario no encontrado");
            }

            Usuario usuario = usuarioOpt.get();
            Restaurante restaurante = usuario.getRestaurante();
            if (restaurante == null) {
                throw new RuntimeException("Restaurante no encontrado");
            }

            Categoria categoria = restaurante.getCategoria().stream()
                    .filter(c -> c.getNombre().equalsIgnoreCase(subcategoriaDTO.getNombreCategoria()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

            Subcategoria subcategoria = new Subcategoria();
            subcategoria.setNombre(subcategoriaDTO.getNombre());
            subcategoria.setDescripcion(subcategoriaDTO.getDescripcion());
            subcategoria.setCategoria(categoria);

            return subcategoriaRepository.save(subcategoria);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
    public String deleteSubcategoriaById (int id_subcategoria) {

        Optional<Subcategoria> subcategoriaOptional = subcategoriaRepository.findById(id_subcategoria);
        if (subcategoriaOptional != null) {
            subcategoriaRepository.deleteById(id_subcategoria);
            return "Subcategoria eliminada exitosamente";
        } else {
            return "Subcategoria no encontrada";
        }

    }

}
