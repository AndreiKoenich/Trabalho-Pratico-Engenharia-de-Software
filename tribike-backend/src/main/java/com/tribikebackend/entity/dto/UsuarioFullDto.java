package com.tribikebackend.entity.dto;

import java.util.List;

import lombok.Data;

@Data
public class UsuarioFullDto {

    private Long id;
    private String picture;
    private String name;
    private String email;
    private String passwordHash;
}
