package com.tribikebackend.entity.dto;

import lombok.Data;

@Data
public class NewUsuarioDto {
    private String username;
    private String email;
    private String password;
}
