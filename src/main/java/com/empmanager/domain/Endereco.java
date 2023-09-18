package com.empmanager.domain;

import com.empmanager.dto.EnderecoDTO;
import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {

    private String logradouro;

    private String numero;

    private String complemento;

    private String cidade;

    private String estado;

    public Endereco() {
    }

    public Endereco(EnderecoDTO enderecoDTO){
        this.logradouro = enderecoDTO.logradouro();
        this.numero = enderecoDTO.numero();
        this.complemento = enderecoDTO.complemento();
        this.cidade = enderecoDTO.cidade();
        this.estado = enderecoDTO.estado();

    }

    public void updateAddress(EnderecoDTO enderecoDTO){
        if(logradouro!=null) {
            this.logradouro = enderecoDTO.logradouro();
        }
        if(numero!= null) {
            this.numero = enderecoDTO.numero();
        }
        if (complemento!=null){
            this.complemento = enderecoDTO.complemento();
        }
        if (cidade!=null) {
            this.cidade = enderecoDTO.cidade();
        }
        if (estado!=null) {
            this.estado = enderecoDTO.estado();
        }
    }



    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
