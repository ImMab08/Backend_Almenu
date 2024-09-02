package org.example.backend_almenu.repository;

import org.example.backend_almenu.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {

    @Query(value = "SELECT * FROM factura WHERE id_factura = ?1", nativeQuery = true)
    Factura findFacturaById(int id_factura);

}
