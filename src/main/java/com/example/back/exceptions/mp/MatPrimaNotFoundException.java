package com.example.back.exceptions.mp;

public class MatPrimaNotFoundException extends RuntimeException{
    public MatPrimaNotFoundException(Long id){
        super("Matéria prima de id : "+id+" não encontrada");
    }
}
