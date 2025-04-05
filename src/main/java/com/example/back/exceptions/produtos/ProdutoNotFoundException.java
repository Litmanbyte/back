package com.example.back.exceptions.produtos;

public class ProdutoNotFoundException extends RuntimeException{
    public ProdutoNotFoundException(Long id){
    super("Produto de "+ id +" não foi encontrado");
    }

}
