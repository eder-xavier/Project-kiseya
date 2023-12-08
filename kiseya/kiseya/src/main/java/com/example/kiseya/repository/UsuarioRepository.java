package com.example.kiseya.repository;

import com.example.kiseya.entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
    Usuario findByCustomQuery(String email);
}
