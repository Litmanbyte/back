package com.example.back.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.back.entity.OP;
@Repository
public interface OPRepository extends JpaRepository<OP,Long> {
    List<OP> findByTerminoIsNull();
    List<OP> findByProdutoId(Long produtoId);
    List<OP> findByInicioBetween(LocalDateTime start, LocalDateTime end);
    boolean existsByNumeroOP(Long numeroOP);
}
