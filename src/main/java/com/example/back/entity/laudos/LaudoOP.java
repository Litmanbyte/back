package com.example.back.entity.laudos;

import com.example.back.entity.OP;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="laudo_op")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LaudoOP extends Laudo{

    @JoinColumn(name = "op_id")
    @OneToOne
    private OP op;
}
