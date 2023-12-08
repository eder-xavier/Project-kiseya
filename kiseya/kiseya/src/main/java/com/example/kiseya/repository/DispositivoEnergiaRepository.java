package com.example.kiseya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kiseya.entitys.DispositivoEnergia;

@Repository
public interface DispositivoEnergiaRepository extends JpaRepository<DispositivoEnergia, Long> {
    // Adicione métodos de consulta personalizados, se necessário
}
