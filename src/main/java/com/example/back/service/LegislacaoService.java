package com.example.back.service;

import com.example.back.entity.Legislacao;
import com.example.back.entity.dto.legislacao.LegislacaoRequestDTO;
import com.example.back.entity.dto.legislacao.LegislacaoResponseDTO;
import com.example.back.repository.LegislacaoRepository;
import com.example.back.entity.dto.mapper.LegislacaoMapper;
import com.example.back.exceptions.legislacao.LegislacaoNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LegislacaoService {

    private final LegislacaoRepository legislacaoRepository;
    private final LegislacaoMapper legislacaoMapper;

    @Transactional
    public LegislacaoResponseDTO create(LegislacaoRequestDTO dto) {
        validarDatas(dto);
        Legislacao legislacao = legislacaoMapper.toEntity(dto);
        return legislacaoMapper.toResponseDTO(legislacaoRepository.save(legislacao));
    }

    @Transactional(readOnly = true)
    public List<LegislacaoResponseDTO> findAll() {
        return legislacaoRepository.findAll().stream()
                .map(legislacaoMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public LegislacaoResponseDTO findById(Long id) {
        return legislacaoMapper.toResponseDTO(
            legislacaoRepository.findById(id)
                .orElseThrow(() -> new LegislacaoNotFoundException(id))
        );
    }

    @Transactional
    public LegislacaoResponseDTO update(Long id, LegislacaoRequestDTO dto) {
        validarDatas(dto);
        Legislacao legislacao = legislacaoRepository.findById(id)
                .orElseThrow(() -> new LegislacaoNotFoundException(id));
        
        legislacao.setDescricao(dto.descricao());
        
        // Atualização controlada do status
        if(!legislacao.getAtiva().equals(dto.status())) {
            if(dto.status()) {
                ativarLegislacao(legislacao);
            } else {
                desativarLegislacao(legislacao, dto.dataFim());
            }
        }
        
        return legislacaoMapper.toResponseDTO(legislacaoRepository.save(legislacao));
    }

    @Transactional
    public void delete(Long id) {
        if(!legislacaoRepository.existsById(id)) {
            throw new LegislacaoNotFoundException(id);
        }
        legislacaoRepository.deleteById(id);
    }

    @Transactional
    public LegislacaoResponseDTO alterarStatus(Long id, Boolean status) {
        Legislacao legislacao = legislacaoRepository.findById(id)
                .orElseThrow(() -> new LegislacaoNotFoundException(id));
        
        if(status) {
            ativarLegislacao(legislacao);
        } else {
            desativarLegislacao(legislacao, new Date());
        }
        
        return legislacaoMapper.toResponseDTO(legislacaoRepository.save(legislacao));
    }

    private void ativarLegislacao(Legislacao legislacao) {
        legislacao.setAtiva(true);
        legislacao.setDataFim(null);
    }

    private void desativarLegislacao(Legislacao legislacao, Date dataFim) {
        if(dataFim == null) {
            throw new IllegalArgumentException("Data fim é obrigatória ao desativar legislação");
        }
        legislacao.setAtiva(false);
        legislacao.setDataFim(dataFim);
    }

    private void validarDatas(LegislacaoRequestDTO dto) {
        if(Boolean.FALSE.equals(dto.status()) && dto.dataFim() == null) {
            throw new IllegalArgumentException("Data fim é obrigatória para legislações inativas");
        }
    }
}