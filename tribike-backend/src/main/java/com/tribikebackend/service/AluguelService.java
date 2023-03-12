package com.tribikebackend.service;

import com.tribikebackend.entity.Aluguel;
import com.tribikebackend.repository.AluguelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {
    private static final Logger log = LoggerFactory.getLogger(AluguelService.class);

    @Autowired
    private AluguelRepository aluguelRepository;

    public List<Aluguel> findAll() {
        return aluguelRepository.findAll();
    }

    public Aluguel save(Aluguel a) {
        return aluguelRepository.save(a);
    }

    public Optional<Aluguel> findById(Long id) {
        return aluguelRepository.findById(id);
    }

    public void update(Aluguel a) {
        aluguelRepository.save(a);
    }

    public void deleteById(Long id) {
        aluguelRepository.deleteById(id);
    }

}
