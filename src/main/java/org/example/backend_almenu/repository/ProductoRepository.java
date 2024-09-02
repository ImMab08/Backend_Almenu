package org.example.backend_almenu.repository;

import org.example.backend_almenu.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query(value = "SELECT * FROM producto WHERE id_producto = ?1", nativeQuery = true)
    Producto findProductoById(int id_producto);

}
