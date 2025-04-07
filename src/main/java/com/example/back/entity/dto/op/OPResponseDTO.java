package com.example.back.entity.dto.op;

import com.example.back.entity.dto.produto.ItemMatPrimaResponseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class OPResponseDTO {
    //private Long id;
    private Long numeroOP;
    private Long lote;
    
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime inicio;
    
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime termino;
    
    private Double quantidadeProd;
    private ProdutoSimplesDTO produto;
    private List<ItemMatPrimaResponseDTO> itens;
    
    // DTOs aninhados para relacionamentos

    public record ProdutoSimplesDTO (
        String nome,
        String marca
    ){}
    
    public record ItemOPSimplesDTO (
        String nome,
        Double quantidade
    ){}

    public record MatPrimaSimplesDTO(
        //Long id,
        String name,
        String unidadeMedida
    ) {}
}