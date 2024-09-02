package org.example.backend_almenu.service;

import org.example.backend_almenu.dto.subcategoria.SubcategoriaDTO;
import org.example.backend_almenu.model.Categoria;
import org.example.backend_almenu.model.Subcategoria;
import org.example.backend_almenu.repository.CategoriaRepository;
import org.example.backend_almenu.repository.RestauranteRepository;
import org.example.backend_almenu.repository.SubcategoriaRepository;
import org.example.backend_almenu.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Subcategoria subcategoria(Subcategoria subcategoria){
        return subcategoriaRepository.getOne(subcategoria.getId());
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

    // Eliminar subcategorias del usuario.

}
