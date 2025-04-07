package com.example.back.exceptions.legislacao;

public class LegislacaoNotFoundException extends RuntimeException {

    public LegislacaoNotFoundException (Long id){
        super("Legislção com id: " + id + " não encontrada");
    }
}
