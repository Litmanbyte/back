package com.example.back.entity.dto.mapper;

import com.example.back.entity.mp.Entrada;
import com.example.back.entity.mp.ItemMatPrima;
import com.example.back.service.mp.EntradaService;

import lombok.RequiredArgsConstructor;

import com.example.back.entity.dto.op.OPResponseDTO.MatPrimaSimplesDTO;
import com.example.back.entity.dto.produto.ItemMatPrimaResponseDTO;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemMatPrimaMapper {

    private final EntradaService entradaService;

    private String buscarLaudo(ItemMatPrima item) {
        Long materiaPrimaId = item.getMateriaPrima().getId();

        List<Entrada> entradas = entradaService.buscarPorMateriaPrima(materiaPrimaId);

        if (entradas == null || entradas.isEmpty()) {
            return null;
        }

        return entradas.stream()
                .filter(e -> e.getLaudo() != null)
                .sorted((e1, e2) -> e2.getDataEntrada().compareTo(e1.getDataEntrada())) 
                .findFirst()
                .map(e -> e.getLaudo().toString())
                .orElse(null);
    }
    
    

    public ItemMatPrimaResponseDTO toResponseDTO(ItemMatPrima item) {
        return new ItemMatPrimaResponseDTO(
         //   item.getId(),
            new MatPrimaSimplesDTO(
              //  item.getMateriaPrima().getId(),
                item.getMateriaPrima().getName(),
                item.getMateriaPrima().getUnidadeMedida()
            ),
            item.getQuantidadeNecessaria(),
            buscarLaudo(item)
        );
    }
}