package com.example.back.service.laudos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back.entity.laudos.Laudo;
import com.example.back.exceptions.laudo.LaudoNotFoundException;

public abstract class BaseLaudoService<T extends Laudo> {
    // Método abstrato: cada serviço concreto deve fornecer seu repositório
    protected abstract JpaRepository<T, Long> getRepository();

    public T salvar(T laudo) {
        return getRepository().save(laudo);
    }

    public T buscarPorId(Long id) {
        return getRepository().findById(id).orElseThrow(() -> new LaudoNotFoundException(id));
    }

    public Page<T> buscaPaginada(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    public void deletar(Long id) {
        getRepository().deleteById(id);
    }
}