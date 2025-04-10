package com.example.back.entity.dto.laudo;

public class LaudoStatusDTO {
    private boolean possuiLaudo;
    private String dataRealizacao;

    // Construtor
    public LaudoStatusDTO(boolean possuiLaudo,  String dataRealizacao) {
        this.possuiLaudo = possuiLaudo;
        this.dataRealizacao = dataRealizacao;
    }

    // Getters
    public boolean isPossuiLaudo() {
        return possuiLaudo;
    }

    public String getdataRealizacao() {
        return dataRealizacao;
    }
}