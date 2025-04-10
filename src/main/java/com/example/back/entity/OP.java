package com.example.back.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ordem_producao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "produto_id")
    @ManyToOne
    private Produto produto;

    private LocalDateTime inicio;
    private LocalDateTime termino;

    @Column(nullable = false)
    private Double quantidadeProd;

    @Column(nullable = false)
    private Long numeroOP;

    @Column(nullable = false)
    private Long lote;

    
}