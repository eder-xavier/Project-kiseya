package com.example.kiseya.entitys;

import java.util.List;

//import javax.persistence.JoinColumns;

//import com.example.kiseya.enums.Papel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
//import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email=:email")

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "localizacao_id")
    private Localizacao localizacao;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String cpfCnpj;

    @Column(name = "is_pessoa_fisica")
    private boolean isPessoaFisica;

    private String senha;


    @OneToMany(mappedBy = "usuario")
    private List<DispositivoEnergia> dispositivosEnergia;
    
    @OneToMany(mappedBy = "comprador")
    private List<TransacaoDispositivo> transacoesDispositivoComprador;
    
    @OneToMany(mappedBy = "vendedor")
    private List<TransacaoDispositivo> transacoesDispositivoVendedor;
    
    @OneToMany(mappedBy = "comprador")
    private List<TransacaoEnergia> transacoesEnergiaComprador;
    
    @OneToMany(mappedBy = "vendedor")
    private List<TransacaoEnergia> transacoesEnergiaVendedor;
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public boolean isPessoaFisica() {
        return isPessoaFisica;
    }

    public void setPessoaFisica(boolean isPessoaFisica) {
        this.isPessoaFisica = isPessoaFisica;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

