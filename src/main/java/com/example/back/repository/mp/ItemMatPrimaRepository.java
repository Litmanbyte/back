package com.example.back.repository.mp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.back.entity.mp.ItemMatPrima;
@Repository
public interface ItemMatPrimaRepository extends JpaRepository<ItemMatPrima, Long> {

    void deleteByProdutoId(Long id);

    List<ItemMatPrima> findByProdutoId(Long produtoId); // Correto
}
