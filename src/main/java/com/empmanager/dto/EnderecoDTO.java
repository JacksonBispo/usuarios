package com.empmanager.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EnderecoDTO(

        @NotBlank
        @Length(min = 10, max = 50, message = "tamanho deve ser de no maximo 50 carateres e no minimo 50")
        @NotBlank
        String logradouro,
        @NotBlank
        String numero,
        String complemento,
        @NotBlank(message = "Cidade é obrigatorio")
        @Length(min = 5, max = 50, message = "tamanho deve ser de no maximo 50 carateres e no minimo 50")
        String cidade,
        @NotBlank(message = "estado é obrigatorio")
                @Length(max = 2)
        String estado) {
}
