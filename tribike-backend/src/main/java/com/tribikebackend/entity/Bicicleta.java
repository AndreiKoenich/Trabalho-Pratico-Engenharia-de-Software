package com.tribikebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bicicleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Usuario usuario; //dono da bicicleta
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
