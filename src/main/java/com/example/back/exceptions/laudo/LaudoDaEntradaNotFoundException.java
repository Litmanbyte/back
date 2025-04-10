package com.example.back.exceptions.laudo;

public class LaudoDaEntradaNotFoundException extends RuntimeException{
    public LaudoDaEntradaNotFoundException (Long id){
        super("Laudo da entrada de Id : " + id + " não encontrada");
    }
}
