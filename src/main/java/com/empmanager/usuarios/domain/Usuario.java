package com.empmanager.usuarios.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String designacao;

    private BigDecimal salario;

    private String telefone;

    @Embedded
    private Endereco endereco;


    public Usuario(){}
    public Usuario(Long id, String name, String designacao, BigDecimal salario, String telefone, Endereco endereco) {
        this.id = id;
        this.name = name;
        this.designacao = designacao;
        this.salario = salario;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
