package com.example.back.repository.laudos;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.back.entity.laudos.LaudoMatPrima;

@Repository
public interface LaudoMatPrimaRepository extends JpaRepository<LaudoMatPrima,Long> {

    boolean existsByEntradaId(Long id);
    Optional<LaudoMatPrima> findByEntradaId(Long entradaId);
    List<LaudoMatPrima> findByDataRealizacaoBetween(Date dataInicio, Date dataFim);
}
