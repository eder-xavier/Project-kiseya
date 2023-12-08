package com.example.kiseya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.kiseya.entitys.TransacaoEnergia;
import com.example.kiseya.services.TransacaoEnergiaService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/transacoes-energia")
public class TransacaoEnergiaController {

    private final TransacaoEnergiaService transacaoEnergiaService;

    @Autowired
    public TransacaoEnergiaController(TransacaoEnergiaService transacaoEnergiaService) {
        this.transacaoEnergiaService = transacaoEnergiaService;
    }

    @GetMapping
    public ResponseEntity<List<TransacaoEnergia>> getAllTransacoes() {
        List<TransacaoEnergia> transacoes = transacaoEnergiaService.getAllTransacoes();
        return new ResponseEntity<>(transacoes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoEnergia> getTransacaoById(@PathVariable Long id) {
        TransacaoEnergia transacao = transacaoEnergiaService.getTransacaoById(id);
        return new ResponseEntity<>(transacao, transacao != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<TransacaoEnergia> saveTransacao(@RequestBody TransacaoEnergia transacao) {
        TransacaoEnergia savedTransacao = transacaoEnergiaService.saveTransacao(transacao);
        return new ResponseEntity<>(savedTransacao, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransacao(@PathVariable Long id) {
        transacaoEnergiaService.deleteTransacao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
