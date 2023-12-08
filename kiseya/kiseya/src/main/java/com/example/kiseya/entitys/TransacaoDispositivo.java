package com.example.kiseya.entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TransacaoDispositivo")
public class TransacaoDispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "comprador_id")//nullable = false
    private Usuario comprador;
    
    @ManyToOne
    @JoinColumn(name = "vendedor_id")//nullable = false
    private Usuario vendedor;
    
    @ManyToOne
    @JoinColumn(name = "dispositivo_id")//nullable = false
    private DispositivoEnergia dispositivoEnergia;
    
    private int quantidade;
    private double precoTotal;

    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Usuario getComprador() {
        return comprador;
    }
    public void setComprador(Usuario comprador) {
        this.comprador = comprador;
    }
    public Usuario getVendedor() {
        return vendedor;
    }
    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }
    public DispositivoEnergia getDispositivoEnergia() {
        return dispositivoEnergia;
    }
    public void setDispositivoEnergia(DispositivoEnergia dispositivoEnergia) {
        this.dispositivoEnergia = dispositivoEnergia;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public double getPrecoTotal() {
        return precoTotal;
    }
    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    
}