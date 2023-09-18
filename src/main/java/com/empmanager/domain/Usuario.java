package com.empmanager.domain;

import com.empmanager.dto.SaveUsuarioDTO;
import com.empmanager.dto.UpdateUsuarioDTO;
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


    public Usuario() {
    }

    public Usuario(SaveUsuarioDTO usuarioDTO) {
        this.name = usuarioDTO.name();
        this.designacao = usuarioDTO.designacao();
        this.salario = usuarioDTO.salario();
        this.telefone = usuarioDTO.telefone();
        this.endereco = new Endereco(usuarioDTO.endereco());

    }

    public Usuario(UpdateUsuarioDTO usuarioDTO) {
        this.id = usuarioDTO.id();
        this.name = usuarioDTO.name();
        this.designacao = usuarioDTO.designacao();
        this.salario = usuarioDTO.salario();
        this.telefone = usuarioDTO.telefone();
        this.endereco = new Endereco(usuarioDTO.endereco());

    }

    public Usuario(String name, String designacao, BigDecimal salario, String telefone, Endereco endereco) {
        this.name = name;
        this.designacao = designacao;
        this.salario = salario;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public void update(UpdateUsuarioDTO usuarioDTO) {
        if (usuarioDTO.name() != null) {
            this.name = usuarioDTO.name();
        }
        if (usuarioDTO.designacao() != null) {
            this.designacao = usuarioDTO.designacao();
        }
        if (usuarioDTO.salario() != null) {
            this.salario = usuarioDTO.salario();
        }

        if (usuarioDTO.telefone() != null) {
            this.telefone = usuarioDTO.telefone();
        }
        if (usuarioDTO.endereco() != null) {
            this.endereco.updateAddress(usuarioDTO.endereco());
        }

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
