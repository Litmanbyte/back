package com.example.back.repository.mp;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.back.entity.mp.Entrada;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Long>{

    List<Entrada> findByFornecedorId(Long fornecedorId);
    
    List<Entrada> findByDataEntradaBetween(Date inicio, Date fim);
    
    boolean existsByLote(String lote);
    
}
