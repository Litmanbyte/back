package com.example.back.entity.mp;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="entradas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Entrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double quantidade;


    @OneToOne
    @JoinColumn(name="fornecedor_id")
    private Fornecedor fornecedor;

    @OneToOne
    @JoinColumn(name="mp_id")
    private MatPrima matPrima;

    @Column(nullable = false)
    private Date dataEntrada;

    @Column(nullable = false)
    private Date validade;

    @Column(nullable = false)
    private String lote;

    @Column(nullable = false)
    private String nf;
}
