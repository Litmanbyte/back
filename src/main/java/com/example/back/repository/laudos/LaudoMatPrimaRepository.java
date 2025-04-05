package com.example.back.repository.laudos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.back.entity.laudos.LaudoMatPrima;

@Repository
public interface LaudoMatPrimaRepository extends JpaRepository<LaudoMatPrima,Long> {

}
