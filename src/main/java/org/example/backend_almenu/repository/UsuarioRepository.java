package org.example.backend_almenu.repository;

import org.example.backend_almenu.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query(value = "SELECT * FROM usuario WHERE email = ?1", nativeQuery = true)
    Usuario getEmail(String email);
}
