package com.example.kiseya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kiseya.entitys.TransacaoDispositivo;
import com.example.kiseya.repository.TransacaoDispositivoRepository;

import java.util.List;

@Service
public class TransacaoDispositivoService {

    private final TransacaoDispositivoRepository transacaoDispositivoRepository;

    @Autowired
    public TransacaoDispositivoService(TransacaoDispositivoRepository transacaoDispositivoRepository) {
        this.transacaoDispositivoRepository = transacaoDispositivoRepository;
    }

    public List<TransacaoDispositivo> getAllTransacoes() {
        return transacaoDispositivoRepository.findAll();
    }

    public TransacaoDispositivo getTransacaoById(Long id) {
        return transacaoDispositivoRepository.findById(id).orElse(null);
    }

    public TransacaoDispositivo saveTransacao(TransacaoDispositivo transacao) {
        return transacaoDispositivoRepository.save(transacao);
    }

    public void deleteTransacao(Long id) {
        transacaoDispositivoRepository.deleteById(id);
    }

}

