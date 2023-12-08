package com.example.kiseya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kiseya.entitys.Localizacao;
import com.example.kiseya.repository.LocalizacaoRepository;

import java.util.List;

@Service
public class LocalizacaoService {

    private final LocalizacaoRepository localizacaoRepository;

    @Autowired
    public LocalizacaoService(LocalizacaoRepository localizacaoRepository) {
        this.localizacaoRepository = localizacaoRepository;
    }

    public List<Localizacao> getAllLocalizacoes() {
        return localizacaoRepository.findAll();
    }

    public Localizacao getLocalizacaoById(Long id) {
        return localizacaoRepository.findById(id).orElse(null);
    }

    public Localizacao saveLocalizacao(Localizacao localizacao) {
        return localizacaoRepository.save(localizacao);
    }

    public void deleteLocalizacao(Long id) {
        localizacaoRepository.deleteById(id);
    }


}
