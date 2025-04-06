package com.example.back.controller;

import com.example.back.entity.dto.produto.ProdutoRequestDTO;
import com.example.back.entity.dto.produto.ProdutoResponseDTO;
import com.example.back.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> create(@RequestBody @Valid ProdutoRequestDTO dto) {
        ProdutoResponseDTO response = produtoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoResponseDTO>> findAll(
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        Page<ProdutoResponseDTO> produtos = produtoService.listarTodasPaginaveis(pageable);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable Long id) {
        ProdutoResponseDTO produto = produtoService.findById(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<ProdutoResponseDTO>> findAtivos() {
        List<ProdutoResponseDTO> produtos = produtoService.buscarPorStatus(true);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/inativos")
    public ResponseEntity<List<ProdutoResponseDTO>> findInativos() {
        List<ProdutoResponseDTO> produtos = produtoService.buscarPorStatus(false);
        return ResponseEntity.ok(produtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid ProdutoRequestDTO dto) {
        ProdutoResponseDTO produtoAtualizado = produtoService.update(id, dto);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ProdutoResponseDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam Boolean status) {
        ProdutoResponseDTO produto = produtoService.atualizarStatus(id, status);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}