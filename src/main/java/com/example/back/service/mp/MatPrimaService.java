package com.example.back.service.mp;

import com.example.back.entity.mp.MatPrima;
import com.example.back.entity.dto.mp.MatPrimaRequestDTO;
import com.example.back.entity.dto.mp.MatPrimaResponseDTO;
import com.example.back.exceptions.mp.MatPrimaNotFoundException;
import com.example.back.repository.mp.MatPrimaRepository;
import com.example.back.entity.dto.mapper.MatPrimaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatPrimaService {

    private final MatPrimaRepository matPrimaRepository;
    private final MatPrimaMapper matPrimaMapper;

    @Transactional
    public MatPrimaResponseDTO create(MatPrimaRequestDTO dto) {
        MatPrima matPrima = matPrimaMapper.toEntity(dto);
        MatPrima saved = matPrimaRepository.save(matPrima);
        return matPrimaMapper.toResponseDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<MatPrimaResponseDTO> findAll() {
        return matPrimaRepository.findAll().stream()
                .map(matPrimaMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public Page<MatPrimaResponseDTO> findAllPaginated(Pageable pageable) {
        return matPrimaRepository.findAll(pageable)
                .map(matPrimaMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public MatPrimaResponseDTO findById(Long id) {
        MatPrima matPrima = matPrimaRepository.findById(id)
                .orElseThrow(() -> new MatPrimaNotFoundException(id));
        return matPrimaMapper.toResponseDTO(matPrima);
    }

    @Transactional
    public MatPrimaResponseDTO update(Long id, MatPrimaRequestDTO dto) {
        MatPrima matPrima = matPrimaRepository.findById(id)
                .orElseThrow(() -> new MatPrimaNotFoundException(id));
        
        matPrima.setName(dto.name());
        matPrima.setDescricao(dto.descricao());
        matPrima.setUnidadeMedida(dto.unidadeMedida());
        
        MatPrima updated = matPrimaRepository.save(matPrima);
        return matPrimaMapper.toResponseDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!matPrimaRepository.existsById(id)) {
            throw new MatPrimaNotFoundException(id);
        }
        matPrimaRepository.deleteById(id);
    }
}