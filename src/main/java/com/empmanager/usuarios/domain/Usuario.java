package com.empmanager.usuarios.domain;

import com.empmanager.dto.UsuarioDTO;
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
    public Usuario(UsuarioDTO usuarioDTO){
        this.name = usuarioDTO.name();
        this.designacao = usuarioDTO.designacao();
        this.salario = usuarioDTO.salario();
        this.endereco = new Endereco(usuarioDTO.enderecoDTO());

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
