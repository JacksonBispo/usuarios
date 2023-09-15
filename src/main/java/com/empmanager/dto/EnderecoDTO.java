package com.empmanager.dto;

import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO(
        @NotBlank
        String logradouro,
        @NotBlank
        String numero,
        String complemento,
        @NotBlank
        String cidade,
        @NotBlank
        String estado) {
}
