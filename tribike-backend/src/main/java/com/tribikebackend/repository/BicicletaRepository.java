package com.tribikebackend.repository;

import com.tribikebackend.entity.Bicicleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BicicletaRepository extends JpaRepository<Bicicleta, Long> {
}
