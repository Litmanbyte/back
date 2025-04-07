package com.example.back.entity.dto.mapper;

import com.example.back.entity.Legislacao;
import com.example.back.entity.dto.legislacao.LegislacaoRequestDTO;
import com.example.back.entity.dto.legislacao.LegislacaoResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class LegislacaoMapper {

    public Legislacao toEntity(LegislacaoRequestDTO dto) {
        Legislacao legislacao = new Legislacao();
        legislacao.setDescricao(dto.descricao());
        legislacao.setAtiva(dto.status());
        legislacao.setDataInicio(dto.dataInicio());
        
        if(Boolean.FALSE.equals(dto.status())) {
            legislacao.setDataFim(dto.dataFim());
        }
        
        return legislacao;
    }

    public LegislacaoResponseDTO toResponseDTO(Legislacao legislacao) {
        return new LegislacaoResponseDTO(
            legislacao.getDescricao(),
            legislacao.getAtiva(),
            legislacao.getDataInicio(),
            legislacao.getDataFim()
        );
    }
}