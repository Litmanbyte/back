package com.example.back.entity.dto.mp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MatPrimaRequestDTO(
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    String name,
    
    @Size(max = 255, message = "Descrição deve ter no máximo 255 caracteres")
    String descricao,
    
    @NotBlank(message = "Unidade de medida é obrigatória")
    @Size(max = 20, message = "Unidade de medida deve ter no máximo 20 caracteres")
    String unidadeMedida
) {}