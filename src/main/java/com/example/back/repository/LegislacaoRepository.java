package com.example.back.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.back.entity.Legislacao;
@Repository
public interface LegislacaoRepository extends JpaRepository<Legislacao, Long>{
    List<Legislacao> findByAtivaTrue();
    List<Legislacao> findByDataFimAfter(Date data);
}
