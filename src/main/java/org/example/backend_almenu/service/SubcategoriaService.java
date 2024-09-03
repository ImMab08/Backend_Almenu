package org.example.backend_almenu.service;

import org.example.backend_almenu.dto.subcategoria.SubcategoriaDTO;
import org.example.backend_almenu.model.Categoria;
import org.example.backend_almenu.model.Restaurante;
import org.example.backend_almenu.model.Subcategoria;
import org.example.backend_almenu.model.Usuario;
import org.example.backend_almenu.repository.CategoriaRepository;
import org.example.backend_almenu.repository.RestauranteRepository;
import org.example.backend_almenu.repository.SubcategoriaRepository;
import org.example.backend_almenu.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<SubcategoriaDTO> getSubcategoriaUsuarioById(int id_usuario){

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id_usuario);
        if(usuarioOptional.isEmpty()){
            throw new RuntimeException("Usuario no encontrado");
        }

        Usuario usuario = usuarioOptional.get();
        Restaurante restaurante = usuario.getRestaurante();

        List<SubcategoriaDTO> subcategoriaDTO = restaurante.getCategoria().stream()
                .flatMap(categoria -> categoria.getSubcategoria().stream())
                    .map(subcategoria -> {
                        SubcategoriaDTO dto = new SubcategoriaDTO();
                        dto.setNombre(subcategoria.getNombre());
                        dto.setDescripcion(subcategoria.getDescripcion());
                        return dto;
                    }).collect(Collectors.toList());

        return subcategoriaDTO;

    }

    // Guardar subcategorias del usuario.
    public SubcategoriaDTO createSubcategoria(SubcategoriaDTO subcategoriaDTO ){
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(subcategoriaDTO.getId_categoria());
        if (categoriaOptional.isEmpty()) {
            throw new RuntimeException("Categoria no encontrada");
        }

        Subcategoria subcategoria = new Subcategoria();
        subcategoria.setNombre(subcategoriaDTO.getNombre());
        subcategoria.setDescripcion(subcategoriaDTO.getDescripcion());
        subcategoria.setCategoria(categoriaOptional.get());

        subcategoriaRepository.save(subcategoria);

        SubcategoriaDTO dto = new SubcategoriaDTO();
        dto.setNombre(subcategoriaDTO.getNombre());
        dto.setDescripcion(subcategoriaDTO.getDescripcion());
        dto.setId_categoria(subcategoria.getId());

        return dto;

    }

    // Actualizar subcategorias del usuario.
    public SubcategoriaDTO updateSubcategoriaUsuarioById(int id_subcategoria, SubcategoriaDTO subcategoriaDTO ){
        Optional<Subcategoria> subcategoriaOptional = subcategoriaRepository.findById(id_subcategoria);
        if (subcategoriaOptional.isEmpty()) {
            throw new RuntimeException("Subcategoria no encontrada");
        }

        Subcategoria subcategoria = subcategoriaOptional.get();

        subcategoria.setNombre(subcategoriaDTO.getNombre());
        subcategoria.setDescripcion(subcategoriaDTO.getDescripcion());

        Subcategoria updateSubcategoria = subcategoriaRepository.save(subcategoria);

        SubcategoriaDTO dto = new SubcategoriaDTO();
        dto.setNombre(updateSubcategoria.getNombre());
        dto.setDescripcion(updateSubcategoria.getDescripcion());
        dto.setId_categoria(updateSubcategoria.getCategoria().getId());

        return dto;
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
