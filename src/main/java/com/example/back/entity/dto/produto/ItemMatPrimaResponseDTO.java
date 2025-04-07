package com.example.back.entity.dto.produto;

import com.example.back.entity.dto.op.OPResponseDTO.MatPrimaSimplesDTO;

public record ItemMatPrimaResponseDTO(
   // Long id,
    MatPrimaSimplesDTO materiaPrima,
    Double quantidadeNecessaria
) {}