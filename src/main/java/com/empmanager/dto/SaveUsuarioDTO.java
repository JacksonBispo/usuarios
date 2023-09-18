package com.empmanager.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public record SaveUsuarioDTO(
        @NotBlank @Length(min = 10, max = 50, message = "tamanho deve ser de no maximo 50 carateres e no minimo 50") String name,
        @NotBlank @Length(min = 10, max = 50, message = "tamanho deve ser de no maximo 50 carateres e no minimo 50") String designacao,
        BigDecimal salario,
        @NotBlank @Length(min = 9, max = 9, message = "tamanho deve ser de no maximo 9 carateres e no minimo 9") String telefone,
        @NotNull @Valid EnderecoDTO endereco) {
}
