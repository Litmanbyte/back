package com.example.back.controller;


import com.example.back.entity.dto.produto.ItemMatPrimaRequestDTO;
import com.example.back.entity.dto.produto.ItemMatPrimaResponseDTO;
import com.example.back.service.ItemMatPrimaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens-matprima")
@RequiredArgsConstructor
public class ItemMatPrimaController {

    private final ItemMatPrimaService itemMatPrimaService;

    @PostMapping
    public ResponseEntity<ItemMatPrimaResponseDTO> create(@RequestBody @Valid ItemMatPrimaRequestDTO dto) {
        ItemMatPrimaResponseDTO response = itemMatPrimaService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<ItemMatPrimaResponseDTO>> findByProdutoId(@PathVariable Long produtoId) {
        List<ItemMatPrimaResponseDTO> response = itemMatPrimaService.findByProdutoId(produtoId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemMatPrimaResponseDTO> findById(@PathVariable Long id) {
        ItemMatPrimaResponseDTO response = itemMatPrimaService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemMatPrimaResponseDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid ItemMatPrimaRequestDTO dto) {
        ItemMatPrimaResponseDTO response = itemMatPrimaService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemMatPrimaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}