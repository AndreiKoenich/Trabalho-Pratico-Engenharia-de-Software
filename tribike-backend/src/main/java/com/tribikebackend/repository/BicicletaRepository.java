package com.tribikebackend.repository;

import com.tribikebackend.entity.Bicicleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BicicletaRepository extends JpaRepository<Bicicleta, Long> {
    List<Bicicleta> findAllByUsuarioId(Long usuarioId);

    @Query(value =
            "SELECT * " +
            "FROM bicicleta b " +
            "WHERE lower(b.bairro) = lower(:bairro) " +
            "  AND b.id NOT IN ( " +
            "    SELECT a.bicicleta_id " +
            "    FROM aluguel a " +
            "    WHERE (data_inicio BETWEEN :dataInicio AND :dataFim " +
            "        OR data_fim BETWEEN :dataInicio AND :dataFim " +
            "        OR :dataInicio BETWEEN data_inicio AND data_fim " +
            "        OR :dataFim BETWEEN data_inicio AND data_fim) " +
            "      AND a.bicicleta_id IS NOT NULL " +
            "  ) ",
            nativeQuery = true
    )
    List<Bicicleta> findAvailable(@Param("bairro") String bairro,
                                  @Param("dataInicio") LocalDate dataInicio,
                                  @Param("dataFim") LocalDate dataFim);

    List<Bicicleta> findByBairroIgnoreCase(@Param("bairro") String bairro);

    @Query(value =
            "SELECT * " +
            "FROM bicicleta b " +
            "WHERE b.id NOT IN ( " +
            "    SELECT a.bicicleta_id " +
            "    FROM aluguel a " +
            "    WHERE (data_inicio BETWEEN :dataInicio AND :dataFim " +
            "        OR data_fim BETWEEN :dataInicio AND :dataFim " +
            "        OR :dataInicio BETWEEN data_inicio AND data_fim " +
            "        OR :dataFim BETWEEN data_inicio AND data_fim) " +
            "      AND a.bicicleta_id IS NOT NULL " +
            ") ",
            nativeQuery = true
    )
    List<Bicicleta> findByDate(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);
}
