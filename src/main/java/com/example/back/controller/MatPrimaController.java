package com.example.back.controller;

import com.example.back.entity.dto.mp.MatPrimaRequestDTO;
import com.example.back.entity.dto.mp.MatPrimaResponseDTO;
import com.example.back.service.mp.MatPrimaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mat-prima")
@RequiredArgsConstructor
public class MatPrimaController {

    private final MatPrimaService matPrimaService;

    @PostMapping
    public ResponseEntity<MatPrimaResponseDTO> create(@RequestBody @Valid MatPrimaRequestDTO dto) {
        MatPrimaResponseDTO response = matPrimaService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<MatPrimaResponseDTO>> findAllPaginated(Pageable pageable) {
        Page<MatPrimaResponseDTO> response = matPrimaService.findAllPaginated(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatPrimaResponseDTO> findById(@PathVariable Long id) {
        MatPrimaResponseDTO response = matPrimaService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatPrimaResponseDTO> update(
            @PathVariable Long id,
            @RequestBody @Valid MatPrimaRequestDTO dto) {
        MatPrimaResponseDTO response = matPrimaService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        matPrimaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}