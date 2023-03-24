package com.tribikebackend.repository;

import com.tribikebackend.entity.Bicicleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BicicletaRepository extends JpaRepository<Bicicleta, Long> {
    List<Bicicleta> findAllByUsuarioId(Long usuarioId);
}
