package com.example.back.repository.mp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.back.entity.mp.MatPrima;

@Repository
public interface MatPrimaRepository extends JpaRepository<MatPrima, Long>{

}
