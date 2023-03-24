package com.tribikebackend.entity.dto;

import lombok.Data;

@Data
public class NewBicicletaDto {

    private Long usuario; //dono da bicicleta
    private String marca;
    private String modelo;
    private String descricao;
    private Float preco;
    private String urlFoto;
    private Float peso;
    private String acessorios;
    private String desconto;
    private String bairro;
}
