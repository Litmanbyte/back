package com.example.back.exceptions.OP;

public class OPAlreadyExistsException extends RuntimeException {
    public OPAlreadyExistsException(Long numeroOP) {
        super("Já existe uma Ordem de Produção com o número " + numeroOP);
    }
}
