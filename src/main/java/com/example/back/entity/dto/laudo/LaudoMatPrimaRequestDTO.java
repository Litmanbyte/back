package com.example.back.entity.dto.laudo;

import java.sql.Date;

public record LaudoMatPrimaRequestDTO(
    Date dataRealizacao,
    String fotoPath) 
    {}
