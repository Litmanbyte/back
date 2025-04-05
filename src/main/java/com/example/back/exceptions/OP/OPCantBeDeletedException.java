package com.example.back.exceptions.OP;

public class OPCantBeDeletedException extends RuntimeException {
    public OPCantBeDeletedException(Long id) {
        super("Ordem de Produção com ID " + id + " não pode ser deletada porque está finalizada");
    }
}
