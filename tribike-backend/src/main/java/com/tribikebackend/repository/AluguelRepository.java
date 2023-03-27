package com.tribikebackend.repository;

import com.tribikebackend.entity.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    List<Aluguel> findAllByLocatarioId(Long id);
}
