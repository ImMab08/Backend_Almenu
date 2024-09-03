package org.example.backend_almenu.service;

import org.example.backend_almenu.model.Categoria;
import org.example.backend_almenu.dto.categoria.CategoriaDTO;
import org.example.backend_almenu.model.Restaurante;
import org.example.backend_almenu.model.Usuario;
import org.example.backend_almenu.repository.CategoriaRepository;

import org.example.backend_almenu.repository.RestauranteRepository;
import org.example.backend_almenu.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    RestauranteRepository restauranteRepository;

    public List<Categoria> categorias() {
        return categoriaRepository.findAll();
    }

    // Traer categorias del usuario por su id.
    public List<CategoriaDTO> getCategoriasUsuarioById(int id_usuario) {

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id_usuario);
        if (usuarioOptional.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        Usuario usuario = usuarioOptional.get();
        Restaurante restaurante = usuario.getRestaurante();

        List<CategoriaDTO> categoriasDTO = restaurante.getCategoria().stream()
                .map(categoria -> {
                  CategoriaDTO dto = new CategoriaDTO();
                  dto.setNombre(categoria.getNombre());
                  dto.setDescripcion(categoria.getDescripcion());
                  return dto;
                }).collect(Collectors.toList());

        return categoriasDTO;

    }

    // Guardar categoría del restaurante del usuario.
    public CategoriaDTO saveCategoriaDto(CategoriaDTO categoriaDTO) {

        // 1. Obtener usuario.
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(categoriaDTO.getId_usuario());
        if (usuarioOptional.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        // 2. Obtener restaurante.
        Optional<Restaurante> restauranteOptional = restauranteRepository.findById(categoriaDTO.getId_restaurante());
        if (restauranteOptional.isEmpty()) {
            throw new RuntimeException("Restaurante no encontrado");
        }

        // 3. Crear y asociar categoria
        Restaurante restaurante = restauranteOptional.get();
        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setDescripcion(categoriaDTO.getDescripcion());
        categoria.setRestaurante(restaurante);

        // 4. Guardar la categoría en la base de datos
        Categoria categoriaGuardada = categoriaRepository.save(categoria);

        // 5. Convertir la entidad guardada a un DTO
        CategoriaDTO dto = new CategoriaDTO();
        dto.setNombre(categoriaGuardada.getNombre());
        dto.setDescripcion(categoriaGuardada.getDescripcion());
        dto.setId_restaurante(restaurante.getId());

        return dto;

    }

    // Actualizar categoria del usuario
    public CategoriaDTO updateCategoriaUsuarioById (int id_categoria, CategoriaDTO categoriaDTO){

        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id_categoria);
        if (categoriaOptional.isEmpty()) {
            throw new RuntimeException("Categoria no encontrada");
        }

        Categoria categoria = categoriaOptional.get();
        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setDescripcion(categoriaDTO.getDescripcion());

        Categoria updateCategoria = categoriaRepository.save(categoria);

        CategoriaDTO dto = new CategoriaDTO();
        dto.setNombre(updateCategoria.getNombre());
        dto.setDescripcion(updateCategoria.getDescripcion());
        dto.setId_restaurante(updateCategoria.getRestaurante().getId());

        return dto;

    }


    // Eliminar categoria del usaurio
    public String deleteCategoriaUsuarioById (int id_categoria) {

        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id_categoria);
        if (categoriaOptional != null) {
            categoriaRepository.deleteById(id_categoria);
            return "Categoria Eliminada con exito";
        } else  {
            return "Categoria no encontrada";
        }

    }

}
