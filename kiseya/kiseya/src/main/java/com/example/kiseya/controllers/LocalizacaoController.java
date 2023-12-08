package com.example.kiseya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.kiseya.entitys.Localizacao;
import com.example.kiseya.services.LocalizacaoService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/localizacoes")
public class LocalizacaoController {

    private final LocalizacaoService localizacaoService;

    @Autowired
    public LocalizacaoController(LocalizacaoService localizacaoService) {
        this.localizacaoService = localizacaoService;
    }

    @GetMapping
    public ResponseEntity<List<Localizacao>> getAllLocalizacoes() {
        List<Localizacao> localizacoes = localizacaoService.getAllLocalizacoes();
        return new ResponseEntity<>(localizacoes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Localizacao> getLocalizacaoById(@PathVariable Long id) {
        Localizacao localizacao = localizacaoService.getLocalizacaoById(id);
        return new ResponseEntity<>(localizacao, localizacao != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Localizacao> saveLocalizacao(@RequestBody Localizacao localizacao) {
        Localizacao savedLocalizacao = localizacaoService.saveLocalizacao(localizacao);
        return new ResponseEntity<>(savedLocalizacao, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocalizacao(@PathVariable Long id) {
        localizacaoService.deleteLocalizacao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

