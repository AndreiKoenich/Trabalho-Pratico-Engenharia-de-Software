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

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String picture;
    private String name;
    private String email;
    private String passwordHash;

    @ColumnDefault(value = "2")
    private int papel;

    public String getPapelString() {
        switch (papel) {
            case 1:
                return "ADMIN";
            case 2:
                return "USUARIO";
            default:
                return "";
        }
    }

}
