package com.example.back.entity.dto.mapper;

import com.example.back.entity.mp.ItemMatPrima;
import com.example.back.entity.dto.op.OPResponseDTO.MatPrimaSimplesDTO;
import com.example.back.entity.dto.produto.ItemMatPrimaResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class ItemMatPrimaMapper {

    public ItemMatPrimaResponseDTO toResponseDTO(ItemMatPrima item) {
        return new ItemMatPrimaResponseDTO(
         //   item.getId(),
            new MatPrimaSimplesDTO(
              //  item.getMateriaPrima().getId(),
                item.getMateriaPrima().getName(),
                item.getMateriaPrima().getUnidadeMedida()
            ),
            item.getQuantidadeNecessaria()
        );
    }
}