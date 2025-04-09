package com.example.back.service.laudos;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.back.entity.OP;
import com.example.back.entity.laudos.LaudoOP;
import com.example.back.repository.laudos.LaudoOPRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LaudoOPService extends BaseLaudoService<LaudoOP> {
    private final LaudoOPRepository repository;

    @Override
    protected JpaRepository<LaudoOP, Long> getRepository() {
        return repository;
    }

    // --- Métodos específicos para LaudoOP ---
    public LaudoOP criarLaudoParaOP(OP op, String fotoPath) {
        LaudoOP laudo = new LaudoOP();
        laudo.setDataRealizacao(new Date());
        laudo.setFotoPath(fotoPath);
        laudo.setOp(op);
        return salvar(laudo);
    }
}