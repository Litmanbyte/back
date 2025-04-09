package com.example.back.entity.dto.mp;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import jakarta.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Email(message = "Email inválido")
    private String email;

    private String endereco;
    private String fone;
    private String fotoDocVerificacao;
}