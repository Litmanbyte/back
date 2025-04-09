package com.example.back.exceptions.mp;

public class FornecedorNotFoundException extends RuntimeException{
    public FornecedorNotFoundException (Long id){
        super("Fornecedor id : "+id+" não encotrado");
    }
}
