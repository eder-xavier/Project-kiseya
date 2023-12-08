package com.example.kiseya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kiseya.entitys.DispositivoEnergia;
import com.example.kiseya.repository.DispositivoEnergiaRepository;

import java.util.List;

@Service
public class DispositivoEnergiaService {

    private final DispositivoEnergiaRepository dispositivoEnergiaRepository;

    @Autowired
    public DispositivoEnergiaService(DispositivoEnergiaRepository dispositivoEnergiaRepository) {
        this.dispositivoEnergiaRepository = dispositivoEnergiaRepository;
    }

    public List<DispositivoEnergia> getAllDispositivosEnergia() {
        return dispositivoEnergiaRepository.findAll();
    }

    public DispositivoEnergia getDispositivoEnergiaById(Long id) {
        return dispositivoEnergiaRepository.findById(id).orElse(null);
    }

    public DispositivoEnergia saveDispositivoEnergia(DispositivoEnergia dispositivoEnergia) {
        return dispositivoEnergiaRepository.save(dispositivoEnergia);
    }

    public void deleteDispositivoEnergia(Long id) {
        dispositivoEnergiaRepository.deleteById(id);
    }

}

