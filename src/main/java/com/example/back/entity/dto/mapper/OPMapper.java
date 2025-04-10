package com.example.back.entity.dto.mapper;

import com.example.back.entity.OP;
import com.example.back.entity.Produto;
import com.example.back.entity.dto.op.OPRequestDTO;
import com.example.back.entity.dto.op.OPResponseDTO;
import com.example.back.entity.dto.op.OPResponseDTO.ProdutoSimplesDTO;
import com.example.back.entity.dto.produto.ItemMatPrimaResponseDTO;
import com.example.back.exceptions.produtos.ProdutoNotFoundException;
import com.example.back.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OPMapper {

    private final ProdutoRepository produtoRepository;
    private final ItemMatPrimaMapper itemMatPrimaMapper;

    public OP toEntity(OPRequestDTO dto) {
        Optional<Produto> p = produtoRepository.findById(dto.getProdutoId());
        if (!p.isPresent()){
            throw new ProdutoNotFoundException(dto.getProdutoId());
        }
        OP op = new OP();
        op.setProduto(p.get());
        op.setQuantidadeProd(dto.getQuantidadeProd());
        op.setNumeroOP(dto.getNumeroOP());
        op.setLote(dto.getLote());
        op.setInicio(dto.getInicio());  // pode estar null
        op.setTermino(dto.getTermino()); // pode estar null
        return op;
    }

    public OPResponseDTO toResponseDTO(OP op) {
        ProdutoSimplesDTO produtoDTO = new ProdutoSimplesDTO(
            op.getProduto().getName(),
            op.getProduto().getMarca()
        );

        List<ItemMatPrimaResponseDTO> itens = produtoRepository
                                        .findById(op.getProduto().getId())
                                        .orElseThrow(() -> new ProdutoNotFoundException(op.getProduto().getId()))
                                        .getMateriasPrimas()
                                        .stream()
                                        .map(itemMatPrimaMapper::toResponseDTO) // Usando method reference                                        
                                        .toList();     
        
        return new OPResponseDTO(
          //  op.getId(),
            op.getNumeroOP(),
            op.getLote(),
            op.getInicio(),
            op.getTermino(),
            op.getQuantidadeProd(),
            produtoDTO,
            itens
        );
    }
}
