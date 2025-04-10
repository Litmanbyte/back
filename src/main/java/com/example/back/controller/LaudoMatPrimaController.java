package com.example.back.controller;

import com.example.back.entity.dto.laudo.LaudoMatPrimaRequestDTO;
import com.example.back.entity.dto.laudo.LaudoMatPrimaResponseDTO;
import com.example.back.entity.laudos.LaudoMatPrima;
import com.example.back.service.laudos.LaudoMatPrimaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/laudos-materia-prima")
@RequiredArgsConstructor
public class LaudoMatPrimaController {

    private final LaudoMatPrimaService laudoService;

    @PostMapping("/entrada/{entradaId}")
    public ResponseEntity<LaudoMatPrimaResponseDTO> criarLaudo(@PathVariable Long entradaId, @RequestBody @Valid LaudoMatPrimaRequestDTO dto) {
        
        laudoService.criarLaudo(entradaId, dto);
        LaudoMatPrimaResponseDTO response = new LaudoMatPrimaResponseDTO(dto.dataRealizacao(), dto.fotoPath());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaudoMatPrimaResponseDTO> buscarPorId(@PathVariable Long id) {
        LaudoMatPrima laudoMatPrima = laudoService.buscarPorId(id);
        LaudoMatPrimaResponseDTO dto = new LaudoMatPrimaResponseDTO(laudoMatPrima.getDataRealizacao(), laudoMatPrima.getFotoPath());
        return ResponseEntity.ok(dto);
        
    }

    @GetMapping("/entrada/{entradaId}")
    public ResponseEntity<LaudoMatPrimaResponseDTO> buscarPorEntradaId(@PathVariable Long entradaId) {
        LaudoMatPrima laudoMatPrima = laudoService.buscarPorEntradaId(entradaId);
        LaudoMatPrimaResponseDTO dto = new LaudoMatPrimaResponseDTO(laudoMatPrima.getDataRealizacao(), laudoMatPrima.getFotoPath());
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<LaudoMatPrimaResponseDTO>> listarTodos() {
        List<LaudoMatPrima> list = laudoService.listarTodos();
        List<LaudoMatPrimaResponseDTO> dtos = list.stream()
            .map(laudo -> new LaudoMatPrimaResponseDTO(laudo.getDataRealizacao(), laudo.getFotoPath()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<LaudoMatPrimaResponseDTO>> listarTodosPaginados(Pageable pageable) {
        Page<LaudoMatPrima> laudosPaginados = laudoService.listarTodosPaginados(pageable);
        Page<LaudoMatPrimaResponseDTO> dtosPaginados = laudosPaginados.map(laudo -> new LaudoMatPrimaResponseDTO(laudo.getDataRealizacao(), laudo.getFotoPath()));
        return ResponseEntity.ok(dtosPaginados);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<LaudoMatPrimaResponseDTO> atualizarLaudo(
            @PathVariable Long id,
            @RequestBody @Valid LaudoMatPrimaRequestDTO dto) {
        LaudoMatPrima laudoAtualizado = laudoService.atualizarLaudo(id, dto);
        LaudoMatPrimaResponseDTO responseDTO = new LaudoMatPrimaResponseDTO(laudoAtualizado.getDataRealizacao(), laudoAtualizado.getFotoPath());
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLaudo(@PathVariable Long id) {
        laudoService.deletarLaudo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/existe/entrada/{entradaId}")
    public ResponseEntity<Boolean> existePorEntradaId(@PathVariable Long entradaId) {
        return ResponseEntity.ok(laudoService.existePorEntradaId(entradaId));
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<LaudoMatPrimaResponseDTO>> buscarPorPeriodo(
            @RequestParam Date inicio,
            @RequestParam Date fim) {
        return ResponseEntity.ok(
            laudoService.buscarPorPeriodo(inicio, fim).stream()
                .map(laudo -> new LaudoMatPrimaResponseDTO(laudo.getDataRealizacao(), laudo.getFotoPath()))
                .toList()
        );
    }
}