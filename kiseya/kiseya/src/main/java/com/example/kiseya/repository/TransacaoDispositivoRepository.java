package com.example.kiseya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kiseya.entitys.TransacaoDispositivo;

@Repository
public interface TransacaoDispositivoRepository extends JpaRepository<TransacaoDispositivo, Long> {
   
}

