package com.example.back.repository.mp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.back.entity.mp.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

}
