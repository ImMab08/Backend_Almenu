package org.example.backend_almenu.repository;

import org.example.backend_almenu.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query(value = "SELECT * FROM usuario WHERE email = ?1", nativeQuery = true)
    Optional<Usuario> findByEmail(String email);

    @Query(value = "SELECT * FROM usuario WHERE id_categoria = ?1", nativeQuery = true)
    Usuario getUsuarioBy(Integer id_usuario);
}
