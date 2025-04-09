package com.example.back.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.back.entity.dto.mp.FornecedorRequestDTO;
import com.example.back.entity.dto.mp.FornecedorResponseDTO;
import com.example.back.service.mp.FornecedorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/fornecedores")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<FornecedorResponseDTO> criar(@Valid @RequestBody FornecedorRequestDTO dto) {
        FornecedorResponseDTO response = fornecedorService.criarFornecedor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorResponseDTO> buscarPorId(@PathVariable Long id) {
        FornecedorResponseDTO response = fornecedorService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FornecedorResponseDTO> atualizar(@PathVariable Long id,@Valid @RequestBody FornecedorRequestDTO dto) {
        FornecedorResponseDTO response = fornecedorService.atualizarFornecedor(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<FornecedorResponseDTO>> listar(@PageableDefault(size = 10) Pageable pageable) {
        Page<FornecedorResponseDTO> response = fornecedorService.listarTodos(pageable);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        fornecedorService.deletarFornecedor(id);
        return ResponseEntity.noContent().build();
    }
}