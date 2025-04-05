package com.example.back.entity.dto.mapper;

import com.example.back.entity.OP;
import com.example.back.entity.dto.op.OPRequestDTO;
import com.example.back.entity.dto.op.OPResponseDTO;

import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring",
        imports = {LocalDateTime.class})
@Component
public interface OPMapper {

    // ------------------- Request DTOs -------------------
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "produto", ignore = true)
    @Mapping(target = "itens", ignore = true)
    @Mapping(target = "inicio", expression = "java(opRequest.getInicio() != null ? opRequest.getInicio() : LocalDateTime.now())")
    OP toEntity(OPRequestDTO opRequest);

    @Mapping(target = "produto", source = "produtoId", qualifiedByName = "mapProdutoFromId")
    OP toEntityWithProduto(OPRequestDTO opRequest);

    // ------------------- Response DTOs -------------------
    @Mapping(target = "status", source = ".", qualifiedByName = "mapStatus")
    @Mapping(target = "produto", qualifiedByName = "mapProdutoDTO")
    OPResponseDTO toResponseDTO(OP op);

    List<OPResponseDTO> toResponseDTOList(List<OP> ops);


    
}