package com.tribikebackend.entity.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NewUsuarioDto {
    private String username;
    private String email;
    private String password;
    private String cpf;
    private String agenciaBancaria;
    private String contaBancaria;
    private LocalDate dataNascimento;
    private String endereco;
    private Integer papel;
}
