package com.example.back.entity.laudos;


import com.example.back.entity.mp.Entrada;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="laudos_mp")
public class LaudoMatPrima extends Laudo{
    
    @JoinColumn(name="mp_id")
    @OneToOne
    private Entrada entrada;

}
