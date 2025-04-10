package com.example.back.controller;

import com.example.back.entity.dto.laudo.LaudoStatusDTO;
import com.example.back.entity.dto.mapper.EntradaMapper;
import com.example.back.entity.dto.mp.EntradaDTO;
import com.example.back.entity.dto.mp.EntradaResponseDTO;
import com.example.back.entity.mp.Entrada;
import com.example.back.service.mp.EntradaService;
import com.example.back.service.mp.EstoqueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/entradas")
@RequiredArgsConstructor
public class EntradaController {

    private final EntradaService entradaService;
    private final EstoqueService estoqueService;

    @PostMapping
    public ResponseEntity<EntradaResponseDTO> criarEntrada(@RequestBody @Valid EntradaDTO dto) {
        Entrada novaEntrada = entradaService.criarEntrada(dto);
        EntradaResponseDTO response = EntradaMapper.toResponseDTO(novaEntrada);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntradaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(EntradaMapper.toResponseDTO(entradaService.buscarPorId(id)));
    }

    @GetMapping
    public ResponseEntity<List<Entrada>> listarTodos() {
        return ResponseEntity.ok(entradaService.listarTodos());
    }

    @GetMapping("/paginado")
    public ResponseEntity<List<EntradaResponseDTO>> listarTodosPaginaveis() {
        List<EntradaResponseDTO> response = entradaService.listarTodos().stream()
                .map(EntradaMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrada> atualizarEntrada(
            @PathVariable Long id,
            @RequestBody @Valid EntradaDTO dto) {
        return ResponseEntity.ok(entradaService.atualizarEntrada(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEntrada(@PathVariable Long id) {
        entradaService.deletarEntrada(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/status-laudo")
    public ResponseEntity<LaudoStatusDTO> verificarSeTemLaudo(@PathVariable Long id) {
        Entrada entrada = entradaService.buscarPorId(id);
        return ResponseEntity.ok(EntradaMapper.mapLaudoStatus(entrada.getLaudo()));
    }

    @GetMapping("/fornecedor/{fornecedorId}")
    public ResponseEntity<List<Entrada>> buscarPorFornecedor(@PathVariable Long fornecedorId) {
        return ResponseEntity.ok(entradaService.buscarPorFornecedor(fornecedorId));
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<Entrada>> buscarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fim) {
        return ResponseEntity.ok(entradaService.buscarPorPeriodo(inicio, fim));
    }

    @PostMapping("/{id}/atualizar-estoque")
    public ResponseEntity<Void> atualizarEstoqueEntrada(
            @PathVariable Long id,
            @RequestParam Double quantidade) {
        Entrada entrada = entradaService.buscarPorId(id);
        
        estoqueService.atualizarEstoque(entrada.getLaudo(), quantidade);
        return ResponseEntity.ok().build();
    }
}