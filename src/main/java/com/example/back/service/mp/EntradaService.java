package com.example.back.service.mp;

import com.example.back.entity.dto.mapper.EntradaMapper;
import com.example.back.entity.dto.mp.EntradaDTO;
import com.example.back.entity.mp.*;
import com.example.back.exceptions.mp.EntradaNotFoundException;
import com.example.back.repository.mp.EntradaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EntradaService {

    private final EntradaRepository entradaRepository;
    private final MatPrimaService matPrimaService;
    private final FornecedorService fornecedorService;

    @Transactional
    public Entrada criarEntrada(EntradaDTO dto) {
        Entrada entrada = EntradaMapper.toEntity(dto);
        entrada.setFornecedor(fornecedorService.findById(dto.idFornecedor()));
        entrada.setMatPrima(matPrimaService.buscarPorId(dto.idMatPrima()));
        return entradaRepository.save(entrada);
    }

    public Entrada buscarPorId(Long id) {
        return entradaRepository.findById(id)
                .orElseThrow(() -> new EntradaNotFoundException(id));
    }

    public List<Entrada> listarTodos() {
        return entradaRepository.findAll();
    }

    public Page<Entrada> listarTodosPaginados(Pageable pageable) {
        return entradaRepository.findAll(pageable);
    }

    @Transactional
    public Entrada atualizarEntrada(Long id, EntradaDTO dto) {
        Entrada entradaExistente = buscarPorId(id);
        
        Entrada entradaAtualizada = EntradaMapper.toEntity(dto);
        entradaAtualizada.setId(entradaExistente.getId());
        entradaAtualizada.setMatPrima(matPrimaService.buscarPorId(dto.idMatPrima()));
        entradaAtualizada.setFornecedor(fornecedorService.findById(dto.idFornecedor()));
        
        return entradaRepository.save(entradaAtualizada);
    }

    @Transactional
    public void deletarEntrada(Long id) {
        Entrada entrada = buscarPorId(id);
        entradaRepository.delete(entrada);
    }

    public boolean existePorId(Long id) {
        return entradaRepository.existsById(id);
    }

    public List<Entrada> buscarPorFornecedor(Long fornecedorId) {
        return entradaRepository.findByFornecedorId(fornecedorId);
    }

    public List<Entrada> buscarPorPeriodo(Date inicio, Date fim) {
        return entradaRepository.findByDataEntradaBetween(inicio, fim);
    }
}