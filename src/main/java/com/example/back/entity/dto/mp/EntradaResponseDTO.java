package com.example.back.entity.dto.mp;

import com.example.back.entity.dto.laudo.LaudoStatusDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EntradaResponseDTO {

    private Long id;
    private Double quantidade;
    private String fornecedorNome;
    private String matPrimaNome;
    private String dataEntrada; // Já formatada como String
    private String validade;    // Já formatada como String
    private String lote;
    private String nf;
    private LaudoStatusDTO laudoStatus;

}