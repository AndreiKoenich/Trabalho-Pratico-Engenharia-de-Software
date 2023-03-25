package com.tribikebackend.entity.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NewAluguelDto {
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Float precoTotal;
    private Long bicicleta;
    private Long locatario;
}
