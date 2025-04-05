package com.example.back.service;

import com.example.back.entity.OP;
import com.example.back.entity.Produto;
import com.example.back.entity.dto.mapper.OPMapper;
import com.example.back.entity.dto.op.OPRequestDTO;
import com.example.back.entity.dto.op.OPResponseDTO;
import com.example.back.exceptions.OP.OPAlreadyExistsException;
import com.example.back.exceptions.OP.OPAlreadyFinishedException;
import com.example.back.exceptions.OP.OPCantBeDeletedException;
import com.example.back.exceptions.OP.OPNotFoundException;
import com.example.back.exceptions.produtos.ProdutoNotFoundException;
import com.example.back.repository.OPRepository;
import com.example.back.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OPService {

    private final OPRepository opRepository;
    private final ProdutoRepository produtoRepository;
    private final OPMapper opMapper;

    @Transactional
    public OPResponseDTO criarOP(OPRequestDTO opRequest) {
        validarNumeroOPUnico(opRequest.getNumeroOP());
        
        Produto produto = buscarProdutoValido(opRequest.getProdutoId());
        
        OP op = opMapper.toEntity(opRequest);
        op.setProduto(produto);
        op.setInicio(LocalDateTime.now());
        
        OP opSalva = opRepository.save(op);
        return opMapper.toResponseDTO(opSalva);
    }

    public OPResponseDTO buscarOPPorId(Long id) {
        OP op = opRepository.findById(id)
                .orElseThrow(() -> new OPNotFoundException(id));
        return opMapper.toResponseDTO(op);
    }

    public Page<OPResponseDTO> listarTodasPaginaveis(Pageable pageable) {
        return opRepository.findAll(pageable)
                .map(opMapper::toResponseDTO);
    }

    public List<OPResponseDTO> listarOpsEmAberto() {
        return opRepository.findByTerminoIsNull().stream()
                .map(opMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<OPResponseDTO> listarPorProduto(Long produtoId) {
        return opRepository.findByProdutoId(produtoId).stream()
                .map(opMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<OPResponseDTO> listarPorPeriodo(LocalDate inicio, LocalDate fim) {
        LocalDateTime start = inicio.atStartOfDay();
        LocalDateTime end = fim.atTime(23, 59, 59);
        
        return opRepository.findByInicioBetween(start, end).stream()
                .map(opMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public OPResponseDTO finalizarOP(Long id) {
        OP op = opRepository.findById(id)
                .orElseThrow(() -> new OPNotFoundException(id));
        
        if (op.getTermino() != null) {
            throw new OPAlreadyFinishedException(id);
        }
        
        op.setTermino(LocalDateTime.now());
        OP opFinalizada = opRepository.save(op);
        
        return opMapper.toResponseDTO(opFinalizada);
    }

    @Transactional
    public void deletarOP(Long id) {
        OP op = opRepository.findById(id)
                .orElseThrow(() -> new OPNotFoundException(id));
        
        if (op.getTermino() != null) {
            throw new OPCantBeDeletedException(id);
        }
        
        opRepository.delete(op);
    }

    // Métodos auxiliares privados
    private void validarNumeroOPUnico(Long numeroOP) {
        if (opRepository.existsByNumeroOP(numeroOP)) {
            throw new OPAlreadyExistsException(numeroOP);
        }
    }

    private Produto buscarProdutoValido(Long produtoId) {
        return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ProdutoNotFoundException(produtoId));
    }

}