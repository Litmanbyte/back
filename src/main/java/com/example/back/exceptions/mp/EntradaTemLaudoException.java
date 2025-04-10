package com.example.back.exceptions.mp;

public class EntradaTemLaudoException extends RuntimeException{
    public EntradaTemLaudoException (Long id){
        super("Entrada de Id : " + id +" já possui lado atribuido");
    }

}
