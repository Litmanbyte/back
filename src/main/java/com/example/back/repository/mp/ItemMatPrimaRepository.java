package com.example.back.repository.mp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.back.entity.mp.ItemMatPrima;

public interface ItemMatPrimaRepository extends JpaRepository<ItemMatPrima, Long> {

    void deleteByProdutoId(Long id);

    List<ItemMatPrima> findByProdutoId(Long produtoId); // Correto
}
