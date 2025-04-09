package com.example.back.service.mp;

import com.example.back.entity.mp.ItemMatPrima;
import com.example.back.entity.Produto;
import com.example.back.entity.mp.MatPrima;
import com.example.back.exceptions.mp.ItemMatPrimaNotFoundException;
import com.example.back.exceptions.produtos.ProdutoNotFoundException;
import com.example.back.exceptions.mp.MatPrimaNotFoundException;
import com.example.back.repository.mp.ItemMatPrimaRepository;
import com.example.back.repository.ProdutoRepository;
import com.example.back.repository.mp.MatPrimaRepository;
import com.example.back.entity.dto.mapper.ItemMatPrimaMapper;
import com.example.back.entity.dto.produto.ItemMatPrimaRequestDTO;
import com.example.back.entity.dto.produto.ItemMatPrimaResponseDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemMatPrimaService {

    private final ItemMatPrimaRepository itemMatPrimaRepository;
    private final ProdutoRepository produtoRepository;
    private final MatPrimaRepository matPrimaRepository;
    private final ItemMatPrimaMapper itemMatPrimaMapper;

    @Transactional
    public ItemMatPrimaResponseDTO create(ItemMatPrimaRequestDTO dto) {
        Produto produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new ProdutoNotFoundException(dto.produtoId()));
        
        MatPrima matPrima = matPrimaRepository.findById(dto.materiaPrimaId())
                .orElseThrow(() -> new MatPrimaNotFoundException(dto.materiaPrimaId()));
        
        ItemMatPrima item = new ItemMatPrima();
        item.setProduto(produto);
        item.setMateriaPrima(matPrima);
        item.setQuantidadeNecessaria(dto.quantidadeNecessaria());
        
        ItemMatPrima saved = itemMatPrimaRepository.save(item);
        return itemMatPrimaMapper.toResponseDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<ItemMatPrimaResponseDTO> findByProdutoId(Long produtoId) {
        return itemMatPrimaRepository.findByProdutoId(produtoId).stream()
                .map(itemMatPrimaMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public ItemMatPrimaResponseDTO findById(Long id) {
        ItemMatPrima item = itemMatPrimaRepository.findById(id)
                .orElseThrow(() -> new ItemMatPrimaNotFoundException(id));
        return itemMatPrimaMapper.toResponseDTO(item);
    }

    @Transactional
    public ItemMatPrimaResponseDTO update(Long id, ItemMatPrimaRequestDTO dto) {
        ItemMatPrima item = itemMatPrimaRepository.findById(id)
                .orElseThrow(() -> new ItemMatPrimaNotFoundException(id));
        
        // Atualiza apenas a quantidade (produto e matéria-prima não devem mudar)
        item.setQuantidadeNecessaria(dto.quantidadeNecessaria());
        
        ItemMatPrima updated = itemMatPrimaRepository.save(item);
        return itemMatPrimaMapper.toResponseDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!itemMatPrimaRepository.existsById(id)) {
            throw new ItemMatPrimaNotFoundException(id);
        }
        itemMatPrimaRepository.deleteById(id);
    }
}