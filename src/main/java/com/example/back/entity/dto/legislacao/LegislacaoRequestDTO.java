package com.example.back.entity.dto.legislacao;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;

public record LegislacaoRequestDTO(
    @NotNull(message = "Descrição é obrigatória")
    String descricao,
    
    @NotNull(message = "Status é obrigatório")
    Boolean status,
    
    @NotNull(message = "Data de início é obrigatória")
    @PastOrPresent(message = "Data de início deve ser no passado ou presente")
    @JsonFormat(pattern = "dd/MM/yyyy")
    Date dataInicio,
    
    @FutureOrPresent(message = "Data fim deve ser no futuro ou presente")
    @JsonFormat(pattern = "dd/MM/yyyy")
    Date dataFim
) {}