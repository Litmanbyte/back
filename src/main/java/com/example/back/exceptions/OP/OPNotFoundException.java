package com.example.back.exceptions.OP;

public class OPNotFoundException extends RuntimeException {
    public OPNotFoundException(Long id) {
        super("Ordem de Produção com ID " + id + " não encontrada");
    }
}
