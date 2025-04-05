package com.example.back.entity;

import java.util.List;

import com.example.back.entity.mp.MatPrima;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String marca;

    @Column(nullable = false)
    private Double preço;//ter um const pra settar preço da embalagem assim entrando no conta pode ser interessante

    @Column(nullable = false)
    private Boolean ativo;//comeca com false,nem sempre vai pra prod direto

    private String comoFazer;

    @Column(nullable = false)
    private String fotoRotulo;

    @ManyToMany
    @JoinTable(
        name = "item_matprima",
        joinColumns = @JoinColumn(name = "item_id"),
        inverseJoinColumns = @JoinColumn(name = "mp_id")
    )
    private List<MatPrima> materiasPrimas;


}
