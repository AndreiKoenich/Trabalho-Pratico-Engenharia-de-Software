package com.tribikebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Float precoTotal;

    @ManyToOne
    private Bicicleta bicicleta;

    @ManyToOne
    private Usuario locador;

    @ManyToOne
    private Usuario locatario;

}
