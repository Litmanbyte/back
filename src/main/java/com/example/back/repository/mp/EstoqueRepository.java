package com.example.back.repository.mp;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.back.entity.laudos.LaudoMatPrima;
import com.example.back.entity.mp.Estoque;
import com.example.back.entity.mp.MatPrima;

import jakarta.transaction.Transactional;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("""
        UPDATE Estoque e
        SET e.emEstoque = :emEstoque
        WHERE e.laudoMatPrima.entrada.matPrima = :matPrima
        """)
    void updateAllEmEstoqueByMatPrima(@Param("matPrima") MatPrima matPrima,
                                    @Param("emEstoque") Boolean emEstoque);




    Optional<Estoque> findByLaudoMatPrima(LaudoMatPrima laudo);

}
