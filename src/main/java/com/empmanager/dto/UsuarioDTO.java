package com.empmanager.dto;

import com.empmanager.usuarios.domain.Endereco;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public record UsuarioDTO(
        Long id,
        @NotBlank
        @Length(min = 10, max = 50, message = "tamanho deve ser de no maximo 50 carateres e no minimo 50")
        String name,
        @NotBlank
        @Length(min = 10, max = 50, message = "tamanho deve ser de no maximo 50 carateres e no minimo 50")

        String designacao,
        BigDecimal salario,
        String telefone,
        String logradouro,
        String numero,
        String complemento,
        String cidade,
        String estado
) {
}
