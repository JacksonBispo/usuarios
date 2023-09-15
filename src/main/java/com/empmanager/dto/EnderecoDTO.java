package com.empmanager.dto;

public record EnderecoDTO(
        String logradouro,
        String numero,
        String complemento,
        String cidade,
        String estado) {
}
