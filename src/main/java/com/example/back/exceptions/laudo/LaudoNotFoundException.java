package com.example.back.exceptions.laudo;

public class LaudoNotFoundException extends RuntimeException {

    public LaudoNotFoundException(Long id){
        super("Laudo de id : " + id +" não foi encontrado");
    }
}
