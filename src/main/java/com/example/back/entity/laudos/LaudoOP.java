package com.example.back.entity.laudos;

import com.example.back.entity.OP;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="laudo_op")
public class LaudoOP extends Laudo{

    @JoinColumn(name = "op_id")
    @OneToOne
    private OP op;
}
