package com.example.back.controller;

import com.example.back.entity.dto.mapper.OPMapper;
import com.example.back.entity.dto.op.OPRequestDTO;
import com.example.back.entity.dto.op.OPResponseDTO;
import com.example.back.service.OPService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ops")
public class OPController {

    private final OPService opService;
    private final OPMapper opMapper;

    @PostMapping
    public ResponseEntity<OPResponseDTO> criarOP(@Valid @RequestBody OPRequestDTO opRequest) {
        OPResponseDTO response = opService.criarOP(opRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OPResponseDTO> buscarOPPorId(@PathVariable Long id) {
        OPResponseDTO response = opService.buscarOPPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<OPResponseDTO>> buscarTodasOPs(Pageable pageable) {
        Page<OPResponseDTO> response = opService.listarTodasPaginaveis(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/em-aberto")
    public ResponseEntity<List<OPResponseDTO>> buscarOPsEmAberto() {
        List<OPResponseDTO> response = opService.listarOpsEmAberto();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/por-produto/{produtoId}")
    public ResponseEntity<List<OPResponseDTO>> buscarOPsPorProduto(@PathVariable Long produtoId) {
        List<OPResponseDTO> response = opService.listarPorProduto(produtoId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/por-periodo")
    public ResponseEntity<List<OPResponseDTO>> buscarOPsPorPeriodo(
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate inicio,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate fim) {
        List<OPResponseDTO> response = opService.listarPorPeriodo(inicio, fim);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity<OPResponseDTO> finalizarOP(@PathVariable Long id) {
        OPResponseDTO response = opService.finalizarOP(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOP(@PathVariable Long id) {
        opService.deletarOP(id);
        return ResponseEntity.noContent().build();
    }

}