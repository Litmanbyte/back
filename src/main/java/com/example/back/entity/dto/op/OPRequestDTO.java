package com.example.back.entity.dto.op;


import java.time.LocalDateTime;
import java.util.Date;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OPRequestDTO {

    @NotNull(message = "Quantidade  é obrigatório")
    private Long produtoId;
    
    @Positive(message = "Quantidade deve ser positiva")
    private Double quantidadeProd;
    
    @Positive(message = "Número OP deve ser positivo")
    @NotNull(message = "Número OP é obrigatório")
    private Long numeroOP;
    
    @Positive(message = "Lote deve ser positivo")
    @NotNull(message = "Lote é obrigatório")
    private Long lote;
    
    private LocalDateTime inicio;
    private LocalDateTime termino;
}