package com.example.kiseya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.kiseya.entitys.DispositivoEnergia;
import com.example.kiseya.services.DispositivoEnergiaService;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/dispositivosEnergia")
public class DispositivoEnergiaController {

    private final DispositivoEnergiaService dispositivoEnergiaService;

    @Autowired
    public DispositivoEnergiaController(DispositivoEnergiaService dispositivoEnergiaService) {
        this.dispositivoEnergiaService = dispositivoEnergiaService;
    }

    @GetMapping
    public ResponseEntity<List<DispositivoEnergia>> getAllDispositivosEnergia() {
        List<DispositivoEnergia> dispositivosEnergia = dispositivoEnergiaService.getAllDispositivosEnergia();
        return new ResponseEntity<>(dispositivosEnergia, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispositivoEnergia> getDispositivoEnergiaById(@PathVariable Long id) {
        DispositivoEnergia dispositivoEnergia = dispositivoEnergiaService.getDispositivoEnergiaById(id);
        return new ResponseEntity<>(dispositivoEnergia, dispositivoEnergia != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<DispositivoEnergia> saveDispositivoEnergia(@RequestBody DispositivoEnergia dispositivoEnergia) {
        DispositivoEnergia savedDispositivoEnergia = dispositivoEnergiaService.saveDispositivoEnergia(dispositivoEnergia);
        return new ResponseEntity<>(savedDispositivoEnergia, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDispositivoEnergia(@PathVariable Long id) {
        dispositivoEnergiaService.deleteDispositivoEnergia(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}