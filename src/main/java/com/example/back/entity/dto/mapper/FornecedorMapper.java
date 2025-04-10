package com.example.back.entity.dto.mapper;

import com.example.back.entity.dto.mp.FornecedorRequestDTO;
import com.example.back.entity.dto.mp.FornecedorResponseDTO;
import com.example.back.entity.mp.Fornecedor;

public class FornecedorMapper {

    public static Fornecedor toEntity(FornecedorRequestDTO dto) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setName(dto.getNome());
        fornecedor.setEmail(dto.getEmail());
        fornecedor.setEndereco(dto.getEndereco());
        fornecedor.setFone(dto.getFone());
        fornecedor.setFotoDocVerificacao(dto.getFotoDocVerificacao());
        return fornecedor;
    }

    public static FornecedorResponseDTO toResponseDTO(Fornecedor fornecedor) {
        FornecedorResponseDTO dto = new FornecedorResponseDTO();
        dto.setNome(fornecedor.getName());
        dto.setEmail(fornecedor.getEmail());
        dto.setEndereco(fornecedor.getEndereco());
        dto.setFone(fornecedor.getFone());
        dto.setFotoDocVerificacao(fornecedor.getFotoDocVerificacao());
        return dto;
    }

    public static void updateEntity(FornecedorRequestDTO dto, Fornecedor fornecedor) {
        fornecedor.setName(dto.getNome());
        fornecedor.setEmail(dto.getEmail());
        fornecedor.setEndereco(dto.getEndereco());
        fornecedor.setFone(dto.getFone());
        fornecedor.setFotoDocVerificacao(dto.getFotoDocVerificacao());
    }
}