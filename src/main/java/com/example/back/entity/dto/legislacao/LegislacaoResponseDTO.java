package com.example.back.entity.dto.legislacao;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public record LegislacaoResponseDTO(
    String descricao,
    Boolean ativa,
    @JsonFormat(pattern = "dd/MM/yyyy")
    Date dataInicio,
    @JsonFormat(pattern = "dd/MM/yyyy")
    Date dataFim
) {}