package org.example.backend_almenu.repository;

import org.example.backend_almenu.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {

    @Query(value = "SELECT * FROM restaurante WHERE id_restaurante = ?1", nativeQuery = true)
    Restaurante findRestauranteById(int id_restaurante);

}
