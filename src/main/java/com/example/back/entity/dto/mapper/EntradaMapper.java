package com.example.back.entity.dto.mapper;

import com.example.back.entity.dto.laudo.LaudoStatusDTO;
import com.example.back.entity.dto.mp.*;
import com.example.back.entity.laudos.LaudoMatPrima;
import com.example.back.entity.mp.Entrada;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntradaMapper {

    public static Entrada toEntity(EntradaDTO dto) {
        if (dto == null) return null;

        Entrada entrada = new Entrada();
        entrada.setQuantidade(dto.quantidade());
        entrada.setDataEntrada(dto.dataEntrada());
        entrada.setValidade(dto.validade());
        entrada.setLote(dto.lote());
        entrada.setNf(dto.nf());
        return entrada;
    }

    public static EntradaResponseDTO toResponseDTO(Entrada entrada) {
        if (entrada == null) return null;

        EntradaResponseDTO response = new EntradaResponseDTO();
        response.setId(entrada.getId());
        response.setQuantidade(entrada.getQuantidade());
        response.setLote(entrada.getLote());
        response.setNf(entrada.getNf());
        response.setDataEntrada(formatDate(entrada.getDataEntrada()));
        response.setValidade(formatDate(entrada.getValidade()));
        
        if (entrada.getFornecedor() != null) {
            response.setFornecedorNome(entrada.getFornecedor().getName());
        }
        
        if (entrada.getMatPrima() != null) {
            response.setMatPrimaNome(entrada.getMatPrima().getName());
        }
        
        response.setLaudoStatus(mapLaudoStatus(entrada.getLaudo()));
        return response;
    }

    public static LaudoStatusDTO mapLaudoStatus(LaudoMatPrima laudo) {
        if (laudo == null) {
            return new LaudoStatusDTO(false, null);
        }
        
        return new LaudoStatusDTO(true,formatDate(laudo.getDataRealizacao()));
    }

    private static String formatDate(Date date) {
        if (date == null) return null;
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
}