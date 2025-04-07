package com.example.back.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back.entity.Produto;
import com.example.back.entity.dto.produto.ProdutoResponseDTO;

public interface ProdutoRepository extends JpaRepository<Produto,Long>{

    Collection<ProdutoResponseDTO> findByAtivo(Boolean ativo);

    boolean existsByName(String name);

}
