package com.tribikebackend.entity.dto;

import com.tribikebackend.entity.Bicicleta;
import lombok.Data;

@Data
public class BicicletaDto {
    private UsuarioMiniDto usuario;
    private String marca;
    private String modelo;
    private String descricao;
    private Float preco;
    private String urlFoto;
    private Float peso;
    private String acessorios;
    private String desconto;
    private String bairro;

    public BicicletaDto(Bicicleta b) {
         marca = b.getMarca();
         modelo = b.getModelo();
         descricao = b.getDescricao();
         preco = b.getPreco();
         urlFoto = b.getUrlFoto();
         peso = b.getPeso();
         acessorios = b.getAcessorios();
         desconto = b.getDesconto();
         bairro = b.getBairro();
         if (b.getUsuario() != null) {
             usuario = new UsuarioMiniDto(b.getUsuario());
         }
    }
}
