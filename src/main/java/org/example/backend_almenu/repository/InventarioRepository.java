package org.example.backend_almenu.repository;

import org.example.backend_almenu.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {

    @Query(value = "SELECT * FROM inventario WHERE id_inventario = ?1", nativeQuery = true)
    Inventario findInventarioById(int id_inventario);

}
