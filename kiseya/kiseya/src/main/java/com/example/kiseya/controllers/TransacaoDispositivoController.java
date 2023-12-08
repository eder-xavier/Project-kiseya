package com.example.kiseya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.kiseya.entitys.TransacaoDispositivo;
import com.example.kiseya.services.TransacaoDispositivoService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/transacoes-dispositivos")
public class TransacaoDispositivoController {

    private final TransacaoDispositivoService transacaoDispositivoService;

    @Autowired
    public TransacaoDispositivoController(TransacaoDispositivoService transacaoDispositivoService) {
        this.transacaoDispositivoService = transacaoDispositivoService;
    }

    @GetMapping
    public ResponseEntity<List<TransacaoDispositivo>> getAllTransacoes() {
        List<TransacaoDispositivo> transacoes = transacaoDispositivoService.getAllTransacoes();
        return new ResponseEntity<>(transacoes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransacaoDispositivo> getTransacaoById(@PathVariable Long id) {
        TransacaoDispositivo transacao = transacaoDispositivoService.getTransacaoById(id);
        return new ResponseEntity<>(transacao, transacao != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<TransacaoDispositivo> saveTransacao(@RequestBody TransacaoDispositivo transacao) {
        TransacaoDispositivo savedTransacao = transacaoDispositivoService.saveTransacao(transacao);
        return new ResponseEntity<>(savedTransacao, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransacao(@PathVariable Long id) {
        transacaoDispositivoService.deleteTransacao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
