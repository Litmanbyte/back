package com.example.back.entity.laudos;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass 
@Setter
@Getter
public abstract class Laudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dataRealizacao;

    @Column(nullable = false)
    private String fotoPath; // caminho no sistema de arquivos
}
