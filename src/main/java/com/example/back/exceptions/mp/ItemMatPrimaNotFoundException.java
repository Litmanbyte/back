package com.example.back.exceptions.mp;

public class ItemMatPrimaNotFoundException extends RuntimeException {
    public ItemMatPrimaNotFoundException(Long id){
        super("Itens de id : "+ id + " não encontrados");
    }
}
