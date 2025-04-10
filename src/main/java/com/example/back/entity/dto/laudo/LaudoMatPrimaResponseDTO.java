package com.example.back.entity.dto.laudo;

import java.util.Date;


public record LaudoMatPrimaResponseDTO(
    Date dataRealizacao,
    String fotoPath
){}