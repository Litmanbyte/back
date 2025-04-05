package com.example.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back.entity.OP;

public interface OPRepository extends JpaRepository<OP,Long> {
    List<OP> findByTerminoIsNull();
    List<OP> findByProdutoId(Long produtoId);
    boolean existsByNumeroOP(Long numeroOP);
}
