package com.example.back.entity.dto.produto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemMatPrimaRequestDTO(
    @NotNull(message = "ID da matéria-prima é obrigatório")
    Long materiaPrimaId,

    @NotNull(message = "ID do produto é obrigatório")
    Long produtoId,
    
    @NotNull(message = "Quantidade é obrigatória")
    @Positive(message = "Quantidade deve ser positiva")
    Double quantidadeNecessaria
) {}

