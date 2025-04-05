package com.example.back.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
    @OneToOne
    private Produto produto;

    private Date inicio;
    private Date termino;

    @Column(nullable = false)
    private Double quantidadeProd;

    @Column(nullable = false)
    private Long numeroOP;

    @Column(nullable = false)
    private Long lote;

    
}