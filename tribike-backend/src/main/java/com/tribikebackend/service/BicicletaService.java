package com.tribikebackend.service;

import com.tribikebackend.entity.Bicicleta;
import com.tribikebackend.repository.BicicletaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BicicletaService {
    private static final Logger log = LoggerFactory.getLogger(BicicletaService.class);

    @Autowired
    private BicicletaRepository bicicletaRepository;

    public List<Bicicleta> findAll() {
        return bicicletaRepository.findAll();
    }

    public Bicicleta save(Bicicleta b) {
        return bicicletaRepository.save(b);
    }

    public Optional<Bicicleta> findById(Long id) {
        return bicicletaRepository.findById(id);
    }

    public void update(Bicicleta b) {
        bicicletaRepository.save(b);
    }

    public void deleteById(Long id) {
        bicicletaRepository.deleteById(id);
    }
}
