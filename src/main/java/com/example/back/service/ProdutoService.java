package com.example.back.service;

import com.example.back.entity.Produto;
import com.example.back.entity.mp.ItemMatPrima;
import com.example.back.entity.mp.MatPrima;
import com.example.back.entity.dto.produto.ProdutoRequestDTO;
import com.example.back.entity.dto.produto.ProdutoResponseDTO;
import com.example.back.entity.dto.produto.ItemMatPrimaRequestDTO;
import com.example.back.exceptions.produtos.ProdutoNotFoundException;
import com.example.back.exceptions.mp.MatPrimaNotFoundException;
import com.example.back.repository.ProdutoRepository;
import com.example.back.repository.mp.ItemMatPrimaRepository;
import com.example.back.repository.mp.MatPrimaRepository;
import com.example.back.entity.dto.mapper.ProdutoMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final MatPrimaRepository matPrimaRepository;
    private final ItemMatPrimaRepository itemMatPrimaRepository;
    private final ProdutoMapper produtoMapper;

    // Métodos CRUD

    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> findAll() {
        return produtoRepository.findAll().stream()
                .map(produtoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProdutoResponseDTO findById(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(id));
        return produtoMapper.toResponseDTO(produto);
    }

    @Transactional
    public ProdutoResponseDTO create(ProdutoRequestDTO dto) {
        if (produtoRepository.existsByName(dto.name())) {
            throw new RuntimeException("Já existe um produto com este nome");
        }
        // Converte DTO para entidade (sem os itens ainda)
        Produto produto = produtoMapper.toEntity(dto);
        
        // Salva o produto primeiro para obter o ID
        Produto produtoSalvo = produtoRepository.save(produto);
        
        // Processa os itens de matéria-prima
        if (dto.materiasPrimas() != null && !dto.materiasPrimas().isEmpty()) {
            adicionarItensMatPrima(produtoSalvo, dto.materiasPrimas());
        }
        
        return produtoMapper.toResponseDTO(produtoSalvo);
    }

    @Transactional
    public ProdutoResponseDTO update(Long id, ProdutoRequestDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(id));
        
        // Atualiza campos básicos
        produto.setName(dto.name());
        produto.setMarca(dto.marca());
        produto.setPreço(dto.preço());
        produto.setAtivo(dto.ativo());
        produto.setComoFazer(dto.comoFazer());
        produto.setFotoRotulo(dto.fotoRotulo());
        
        // Atualiza itens de matéria-prima
        if (dto.materiasPrimas() != null) {
            atualizarItensMatPrima(produto, dto.materiasPrimas());
        }
        
        Produto produtoAtualizado = produtoRepository.save(produto);
        return produtoMapper.toResponseDTO(produtoAtualizado);
    }

    @Transactional
    public void delete(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(id));
        
        // Remove primeiro os itens para evitar problemas de constraint
        itemMatPrimaRepository.deleteByProdutoId(id);
        produtoRepository.delete(produto);
    }

    public Page<ProdutoResponseDTO> listarTodasPaginaveis(Pageable pageable) {
        return produtoRepository.findAll(pageable)
                .map(produtoMapper::toResponseDTO);
    }

    // Métodos auxiliares para gerenciar os itens de matéria-prima

    private void adicionarItensMatPrima(Produto produto, List<ItemMatPrimaRequestDTO> itensDTO) {
        itensDTO.forEach(itemDTO -> {
            MatPrima matPrima = matPrimaRepository.findById(itemDTO.materiaPrimaId())
                    .orElseThrow(() -> new MatPrimaNotFoundException(itemDTO.materiaPrimaId()));
            
            ItemMatPrima item = new ItemMatPrima();
            item.setProduto(produto);
            item.setMateriaPrima(matPrima);
            item.setQuantidadeNecessaria(itemDTO.quantidadeNecessaria());
            
            itemMatPrimaRepository.save(item);
        });
    }

    private void atualizarItensMatPrima(Produto produto, List<ItemMatPrimaRequestDTO> novosItensDTO) {
        // 1. Remove todos os itens existentes
        itemMatPrimaRepository.deleteByProdutoId(produto.getId());
        
        // 2. Adiciona todos os novos itens
        novosItensDTO.forEach(itemDTO -> {
            MatPrima matPrima = matPrimaRepository.findById(itemDTO.materiaPrimaId())
                    .orElseThrow(() -> new MatPrimaNotFoundException(itemDTO.materiaPrimaId()));
            
            ItemMatPrima novoItem = new ItemMatPrima();
            novoItem.setProduto(produto);
            novoItem.setMateriaPrima(matPrima);
            novoItem.setQuantidadeNecessaria(itemDTO.quantidadeNecessaria());
            itemMatPrimaRepository.save(novoItem);
        });
    }

    // Métodos adicionais úteis

    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> buscarPorStatus(Boolean ativo) {
        return produtoRepository.findByAtivo(ativo).stream()
              //  .map(produtoMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProdutoResponseDTO atualizarStatus(Long id, Boolean status) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(id));
        produto.setAtivo(status);
        return produtoMapper.toResponseDTO(produtoRepository.save(produto));
    }
}