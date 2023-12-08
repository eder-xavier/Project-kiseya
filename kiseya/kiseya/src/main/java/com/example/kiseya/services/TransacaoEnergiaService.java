package com.example.kiseya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kiseya.entitys.TransacaoEnergia;
import com.example.kiseya.repository.TransacaoEnergiaRepository;

import java.util.List;

@Service
public class TransacaoEnergiaService {

    private final TransacaoEnergiaRepository transacaoEnergiaRepository;

    @Autowired
    public TransacaoEnergiaService(TransacaoEnergiaRepository transacaoEnergiaRepository) {
        this.transacaoEnergiaRepository = transacaoEnergiaRepository;
    }

    public List<TransacaoEnergia> getAllTransacoes() {
        return transacaoEnergiaRepository.findAll();
    }

    public TransacaoEnergia getTransacaoById(Long id) {
        return transacaoEnergiaRepository.findById(id).orElse(null);
    }

    public TransacaoEnergia saveTransacao(TransacaoEnergia transacao) {
        return transacaoEnergiaRepository.save(transacao);
    }

    public void deleteTransacao(Long id) {
        transacaoEnergiaRepository.deleteById(id);
    }

}
