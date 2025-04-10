package com.example.back.entity.laudos;


import com.example.back.entity.mp.Entrada;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="laudos_mp")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LaudoMatPrima extends Laudo{
    
    @JoinColumn(name="mp_id")
    @OneToOne
    private Entrada entrada;
    
    @Override
    public String toString() {
        return this.entrada.getLote(); 
    }

    
}
