package com.example.back.exceptions.mp;

public class EntradaNotFoundException extends RuntimeException {

    public EntradaNotFoundException(Long id){
        super("Entrada de id :" + id +" não encontrada"); 
    }
}
