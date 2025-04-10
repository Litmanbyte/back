package com.example.back.service.mp;

import org.springframework.stereotype.Service;

import com.example.back.entity.laudos.LaudoMatPrima;
import com.example.back.entity.mp.Estoque;
import com.example.back.repository.mp.EstoqueRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    @Transactional
    public void atualizarEstoque(LaudoMatPrima laudo, Double quantidade) {
        // Validação direta (se o laudo tem entrada e matéria-prima)
        if (laudo == null || laudo.getEntrada() == null) {
            throw new IllegalArgumentException("Laudo sem entrada associada");
        }
    
        // 1. Desativa TODOS os registros da matéria-prima associada ao laudo
        estoqueRepository.updateAllEmEstoqueByMatPrima(laudo.getEntrada().getMatPrima(), false);
    
        // 2. Atualiza ou cria o registro do estoque
        Estoque estoque = estoqueRepository.findByLaudoMatPrima(laudo)
            .orElseGet(() -> {
                Estoque novo = new Estoque();
                novo.setLaudoMatPrima(laudo); 
                // Não é necessário setar matPrima aqui, pois ela é acessível via laudo.getEntrada().getMatPrima()
                return novo;
            });
    
        estoque.setEmEstoque(true);
        estoqueRepository.save(estoque);
    }
}
