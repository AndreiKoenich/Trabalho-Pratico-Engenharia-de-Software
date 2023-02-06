package com.tribikebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String email;
    private String passwordHash;
    private String cpf;
    private String agenciaBancaria;
    private String contaBancaria;
    private LocalDate dataNascimento;
    private String endereco;

    @ColumnDefault(value = "2")
    private int papel;

    public String getPapelString() {
        switch (papel) {
            case 1:
                return "ADMIN";
            case 2:
                return "LOCADOR";
            case 3:
                return "LOCATÁRIO";
            default:
                return "";
        }
    }

}
