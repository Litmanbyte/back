package com.example.back.entity.dto.op;


import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OPRequestDTO {

    @NotNull(message = "Produto é obrigatório")
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