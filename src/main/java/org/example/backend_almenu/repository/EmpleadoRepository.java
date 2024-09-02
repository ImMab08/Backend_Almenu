package org.example.backend_almenu.repository;

import org.example.backend_almenu.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    @Query(value = "SELECT * FROM empleado WHERE id_empleado = ?1", nativeQuery = true)
    Empleado findEmpleadoById(int id_empleado);

}
