package com.example.back.controller;

import com.example.back.entity.dto.legislacao.LegislacaoRequestDTO;
import com.example.back.entity.dto.legislacao.LegislacaoResponseDTO;
import com.example.back.service.LegislacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/legislacoes")
@RequiredArgsConstructor
public class LegislacaoController {

    private final LegislacaoService legislacaoService;

    @PostMapping
    public ResponseEntity<LegislacaoResponseDTO> create(@RequestBody @Valid LegislacaoRequestDTO dto) {
        LegislacaoResponseDTO response = legislacaoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<LegislacaoResponseDTO>> findAll() {
        List<LegislacaoResponseDTO> response = legislacaoService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LegislacaoResponseDTO> findById(@PathVariable Long id) {
        LegislacaoResponseDTO response = legislacaoService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LegislacaoResponseDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid LegislacaoRequestDTO dto) {
        LegislacaoResponseDTO response = legislacaoService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<LegislacaoResponseDTO> alterarStatus(
            @PathVariable Long id,
            @RequestParam Boolean status) {
        LegislacaoResponseDTO response = legislacaoService.alterarStatus(id, status);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        legislacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}