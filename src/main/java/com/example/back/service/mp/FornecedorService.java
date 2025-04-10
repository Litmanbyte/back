package com.example.back.service.mp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.back.entity.dto.mapper.FornecedorMapper;
import com.example.back.entity.dto.mp.FornecedorRequestDTO;
import com.example.back.entity.dto.mp.FornecedorResponseDTO;
import com.example.back.entity.mp.Fornecedor;
import com.example.back.exceptions.mp.FornecedorNotFoundException;
import com.example.back.repository.mp.FornecedorRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    @Transactional
    public FornecedorResponseDTO criarFornecedor(FornecedorRequestDTO dto) {
        Fornecedor fornecedor = FornecedorMapper.toEntity(dto);
        Fornecedor salvo = fornecedorRepository.save(fornecedor);
        return FornecedorMapper.toResponseDTO(salvo);
    }

    public FornecedorResponseDTO buscarPorId(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
            .orElseThrow(() -> new FornecedorNotFoundException(id));
        return FornecedorMapper.toResponseDTO(fornecedor);
    }

    public Fornecedor findById(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
            .orElseThrow(() -> new FornecedorNotFoundException(id));
        return fornecedor;
    }

    @Transactional
    public FornecedorResponseDTO atualizarFornecedor(Long id, FornecedorRequestDTO dto) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
            .orElseThrow(() -> new FornecedorNotFoundException(id));
        
        FornecedorMapper.updateEntity(dto, fornecedor); // Atualiza os campos
        Fornecedor atualizado = fornecedorRepository.save(fornecedor);
        return FornecedorMapper.toResponseDTO(atualizado);
    }

    public Page<FornecedorResponseDTO> listarTodos(Pageable pageable) {
        return fornecedorRepository.findAll(pageable)
            .map(FornecedorMapper::toResponseDTO);
    }

    @Transactional
    public void deletarFornecedor(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
            .orElseThrow(() -> new FornecedorNotFoundException(id));
        fornecedorRepository.delete(fornecedor);
    }
}