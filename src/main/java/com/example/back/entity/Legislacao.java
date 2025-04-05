package com.example.back.entity;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="legislacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Legislacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private Boolean ativa;//deve começar como true
    
    @Column(nullable = false)
    private Date dataInicio;
    private Date dataFim; //quando passar ativa pra false, settar a isso
}
