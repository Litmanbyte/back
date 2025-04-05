package com.example.back.service;

import com.example.back.entity.OP;
import com.example.back.entity.Produto;
import com.example.back.exceptions.OP.OPCantBeDeletedException;
import com.example.back.exceptions.OP.OPNotFoundException;
import com.example.back.repository.OPRepository;
import com.example.back.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OPService {

    private final OPRepository opRepository;
    private final ProdutoRepository produtoRepository;


    @Transactional
    public OP criarOP(Long produtoId, Double quantidadeProd, Long numeroOP, Long lote) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new OPNotFoundException(produtoId));
        
        OP op = new OP();
        op.setProduto(produto);
        op.setQuantidadeProd(quantidadeProd);
        op.setNumeroOP(numeroOP);
        op.setLote(lote);
        op.setInicio(new Date()); 
        
        return opRepository.save(op);
    }

    @Transactional
    public OP atualizarOP(Long opId, OP opAtualizada) {
        OP opExistente = opRepository.findById(opId)
                .orElseThrow(() -> new OPNotFoundException(opId));
        
        opExistente.setQuantidadeProd(opAtualizada.getQuantidadeProd());
        opExistente.setNumeroOP(opAtualizada.getNumeroOP());
        opExistente.setLote(opAtualizada.getLote());
        
        return opRepository.save(opExistente);
    }

    @Transactional
    public OP finalizarOP(Long opId) {
        OP op = opRepository.findById(opId)
                .orElseThrow(() -> new OPNotFoundException(opId));
        
        op.setTermino(new Date());
        return opRepository.save(op);
    }

    public List<OP> listarTodas() {
        return opRepository.findAll();
    }

    public List<OP> listarOpsEmAberto() {
        return opRepository.findByTerminoIsNull();
    }

    public List<OP> listarPorProduto(Long produtoId) {
        return opRepository.findByProdutoId(produtoId);
    }

    public boolean numeroOPExiste(Long numeroOP) {
        return opRepository.existsByNumeroOP(numeroOP);
    }

    @Transactional
    public void deletarOP(Long opId) {
        OP op = opRepository.findById(opId)
                .orElseThrow(() -> new OPNotFoundException(opId));
        
        if (op.getTermino() != null) {
            throw new OPCantBeDeletedException(opId);
        }
        
        opRepository.delete(op);
    }

}