package com.example.back.entity.dto.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;

public record ProdutoRequestDTO(
    @NotBlank(message = "Nome é obrigatório")
    String name,
    
    String marca,
    
    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser positivo")
    Double preço,
    
    @NotNull(message = "Status ativo é obrigatório")
    Boolean ativo,
    
    String comoFazer,
    
    @NotBlank(message = "Foto do rótulo é obrigatória")
    String fotoRotulo,
    
    List<ItemMatPrimaRequestDTO> materiasPrimas
) {}