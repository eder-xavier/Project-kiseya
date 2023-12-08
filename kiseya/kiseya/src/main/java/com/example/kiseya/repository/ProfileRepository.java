package com.example.kiseya.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.kiseya.entitys.Profile;
import com.example.kiseya.entitys.Usuario;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUsuario(Usuario usuario);
}
