package com.example.back.entity.dto.mapper;

import com.example.back.entity.mp.MatPrima;
import com.example.back.entity.dto.mp.MatPrimaRequestDTO;
import com.example.back.entity.dto.mp.MatPrimaResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class MatPrimaMapper {

    public MatPrima toEntity(MatPrimaRequestDTO dto) {
        MatPrima matPrima = new MatPrima();
        matPrima.setName(dto.name());
        matPrima.setDescricao(dto.descricao());
        matPrima.setUnidadeMedida(dto.unidadeMedida());
        return matPrima;
    }

    public MatPrimaResponseDTO toResponseDTO(MatPrima matPrima) {
        return new MatPrimaResponseDTO(
            matPrima.getName(),
            matPrima.getDescricao(),
            matPrima.getUnidadeMedida()
        );
    }
}