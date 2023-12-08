package com.example.kiseya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kiseya.entitys.TransacaoEnergia;

@Repository
public interface TransacaoEnergiaRepository extends JpaRepository<TransacaoEnergia, Long> {
   
}
