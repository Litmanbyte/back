package com.example.back.entity.dto.mp;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record EntradaDTO(
    @NotNull(message = "Quantidade é obrigatória")
    Double quantidade,
    @PastOrPresent(message = "Data de início deve ser no passado ou presente")
    @JsonFormat(pattern = "dd/MM/yyyy")
    Date dataEntrada,
    @Future(message = "Deve ser no futuro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    Date validade,
    @NotNull(message = "Descrição é obrigatória")
    String lote,
    @NotNull(message = "NF é obrigatória")
    String nf,
    @NotNull(message = "id fornecedor obrigatório")
    Long idFornecedor,
    @NotNull(message = "id matéria-prima é obrigatório")
    Long idMatPrima
) {}
