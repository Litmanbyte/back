package com.example.back.entity.dto.mapper;

import com.example.back.entity.Produto;
import com.example.back.entity.dto.produto.ProdutoRequestDTO;
import com.example.back.entity.dto.produto.ProdutoResponseDTO;
import com.example.back.entity.dto.produto.ItemMatPrimaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProdutoMapper {

    private final ItemMatPrimaMapper itemMatPrimaMapper;

    public Produto toEntity(ProdutoRequestDTO dto) {
        Produto produto = new Produto();
        produto.setName(dto.name());
        produto.setMarca(dto.marca());
        produto.setPreço(dto.preço());
        produto.setAtivo(dto.ativo());
        produto.setComoFazer(dto.comoFazer());
        produto.setFotoRotulo(dto.fotoRotulo());
        
        // Itens de matéria-prima serão tratados no service para melhor controle transacional
        return produto;
    }

    //TODO colocar no retorno da lista o lote da materia prima
    public ProdutoResponseDTO toResponseDTO(Produto produto) {
        List<ItemMatPrimaResponseDTO> itensDTO = produto.getMateriasPrimas() != null ?
            produto.getMateriasPrimas().stream()
                .map(itemMatPrimaMapper::toResponseDTOSemLaudo)
                .collect(Collectors.toList()) :
            List.of();
        
        return new ProdutoResponseDTO(
          //  produto.getId(),
            produto.getName(),
            produto.getMarca(),
            produto.getPreço(),
            produto.getAtivo(),
            produto.getComoFazer(),
            produto.getFotoRotulo(),
            itensDTO
        );
    }
}