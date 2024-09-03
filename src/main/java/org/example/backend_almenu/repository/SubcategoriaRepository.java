package org.example.backend_almenu.repository;

import org.example.backend_almenu.model.Subcategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Integer> {

    @Query(value = "SELECT * FROM subcategoria WHERE nombre = ?1", nativeQuery = true)
    Subcategoria findSubcategoriaByNombre(String nombre);

}
