package com.example.back.service.laudos;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.back.entity.dto.laudo.LaudoMatPrimaRequestDTO;
import com.example.back.entity.laudos.LaudoMatPrima;
import com.example.back.exceptions.laudo.LaudoDaEntradaNotFoundException;
import com.example.back.exceptions.laudo.LaudoNotFoundException;
import com.example.back.exceptions.mp.EntradaTemLaudoException;
import com.example.back.repository.laudos.LaudoMatPrimaRepository;
import lombok.RequiredArgsConstructor;

import com.example.back.entity.mp.*;
import com.example.back.service.mp.EntradaService;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LaudoMatPrimaService {

    private final LaudoMatPrimaRepository laudoRepository;
    private final EntradaService entradaService;

    // CREATE
    @Transactional
    public LaudoMatPrima criarLaudo(Long entradaId, LaudoMatPrimaRequestDTO dto) {
        Entrada entrada = entradaService.buscarPorId(entradaId);
        
        if (laudoRepository.existsByEntradaId(entradaId)) {
            throw new EntradaTemLaudoException(entradaId);
        }

        LaudoMatPrima laudo = new LaudoMatPrima();
        laudo.setDataRealizacao(dto.dataRealizacao());   
        laudo.setFotoPath(dto.fotoPath());  

        laudo.setEntrada(entrada);           
        entrada.setLaudo(laudo); // Atualiza os dois lados da relação
        // o transational att todo mundo mesmo sem o save da entrada
        
        return laudoRepository.save(laudo);
    }

    public LaudoMatPrima buscarPorId(Long id) {
        return laudoRepository.findById(id)
                .orElseThrow(() -> new LaudoNotFoundException(id));
    }

    public LaudoMatPrima buscarPorEntradaId(Long entradaId) {
        return laudoRepository.findByEntradaId(entradaId)
                .orElseThrow(() -> new LaudoDaEntradaNotFoundException(entradaId));
    }

    public List<LaudoMatPrima> listarTodos() {
        return laudoRepository.findAll();
    }

    public Page<LaudoMatPrima> listarTodosPaginados(Pageable pageable) {
        return laudoRepository.findAll(pageable);
    }

    @Transactional
    public LaudoMatPrima atualizarLaudo(Long id, LaudoMatPrimaRequestDTO dto) {
        LaudoMatPrima laudoExistente = buscarPorId(id);
        
        laudoExistente.setDataRealizacao(dto.dataRealizacao());
        laudoExistente.setFotoPath(dto.fotoPath());
        
        return laudoRepository.save(laudoExistente);
    }

    @Transactional
    public void deletarLaudo(Long id) {
        LaudoMatPrima laudo = buscarPorId(id);
        Entrada entrada = laudo.getEntrada();
        
        entrada.setLaudo(null); // Remove a referência
        laudoRepository.delete(laudo);
    }

    public boolean existePorEntradaId(Long entradaId) {
        return laudoRepository.existsByEntradaId(entradaId);
    }

    public List<LaudoMatPrima> buscarPorPeriodo(Date inicio, Date fim) {
        return laudoRepository.findByDataRealizacaoBetween(inicio, fim);
    }
}