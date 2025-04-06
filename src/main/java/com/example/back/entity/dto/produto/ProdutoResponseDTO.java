package com.example.back.entity.dto.produto;

import java.util.List;

public record ProdutoResponseDTO(
    Long id,
    String name,
    String marca,
    Double preço,
    Boolean ativo,
    String comoFazer,
    String fotoRotulo,
    List<ItemMatPrimaResponseDTO> materiasPrimas
) {}