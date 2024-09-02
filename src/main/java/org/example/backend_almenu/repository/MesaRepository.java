package org.example.backend_almenu.repository;

import org.example.backend_almenu.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Integer> {

    @Query(value = "SELECT * FROM mesa WHERE id_factura = ?1", nativeQuery = true)
    Mesa findMesaById(int id_mesa);

}