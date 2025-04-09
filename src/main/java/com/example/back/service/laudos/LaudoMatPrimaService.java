package com.example.back.service.laudos;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.back.entity.laudos.LaudoMatPrima;
import com.example.back.entity.mp.Entrada;
import com.example.back.exceptions.mp.EntradaNotFoundException;
import com.example.back.repository.laudos.LaudoMatPrimaRepository;
import com.example.back.repository.mp.EntradaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor  
public class LaudoMatPrimaService extends BaseLaudoService<LaudoMatPrima> {

    private final LaudoMatPrimaRepository repository;
    private final EntradaRepository entradaRepository;

    @Override
    protected JpaRepository<LaudoMatPrima, Long> getRepository() {
        return repository;  // Retorna o repositório específico
    }

    // --- Métodos específicos para LaudoMatPrima ---
    public LaudoMatPrima criarLaudoParaEntrada(Long entradaId, String fotoPath) {
        LaudoMatPrima laudo = new LaudoMatPrima();
        laudo.setDataRealizacao(new Date());
        laudo.setFotoPath(fotoPath);
        laudo.setEntrada(entradaRepository.findById(entradaId).orElseThrow(() -> new EntradaNotFoundException(entradaId)));
        return salvar(laudo);  
    }
}