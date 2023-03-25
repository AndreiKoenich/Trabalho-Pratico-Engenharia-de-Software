package com.tribikebackend.entity.dto;

import com.tribikebackend.entity.Aluguel;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AluguelDto {
    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Float precoTotal;
    private BicicletaDto bicicleta;
    private UsuarioMiniDto locador;
    private UsuarioMiniDto locatario;

    public AluguelDto(Aluguel a) {
        id = a.getId();
        dataInicio = a.getDataInicio();
        dataFim = a.getDataFim();
        precoTotal = a.getPrecoTotal();
        if (a.getBicicleta() != null) {
            bicicleta = new BicicletaDto(a.getBicicleta());
        }
        if (a.getLocador() != null) {
            locador = new UsuarioMiniDto(a.getLocador());
        }
        if (a.getLocatario() != null) {
            locatario = new UsuarioMiniDto(a.getLocatario());
        }
    }
}
