package com.tribikebackend.entity.dto;

import com.tribikebackend.entity.Usuario;

import lombok.Data;

@Data
public class UsuarioMiniDto {

    private Long id;
    private String nome;
    private String email;

    public UsuarioMiniDto(Usuario usuario) {
        id = usuario.getId();
        nome = usuario.getUsername();
        email = usuario.getEmail();
    }
}
