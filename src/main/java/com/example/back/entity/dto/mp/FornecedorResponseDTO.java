package com.example.back.entity.dto.mp;




import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorResponseDTO {
    private String nome;
    private String email;
    private String endereco;
    private String fone;
    private String fotoDocVerificacao;
}