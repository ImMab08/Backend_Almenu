package org.example.backend_almenu.repository;

import org.example.backend_almenu.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    @Query(value = "SELECT * FROM categoria WHERE nombre = ?1", nativeQuery = true)
    Categoria findCategoriaByNombre(String nombre);

}
