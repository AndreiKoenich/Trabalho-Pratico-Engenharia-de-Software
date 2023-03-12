package com.tribikebackend.controller;

import com.tribikebackend.entity.Aluguel;
import com.tribikebackend.service.AluguelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluguel")
public class AluguelController {
    private static final Logger log = LoggerFactory.getLogger(AluguelController.class);

    @Autowired
    private AluguelService aluguelService;

    @GetMapping("")
    public List<Aluguel> getAlugueis() {
        log.info("GET /aluguel");
        List<Aluguel> output = aluguelService.findAll();
        log.info("{} alugueis encontrados", output.size());
        return output;
    }

    @PostMapping("")
    public void postAluguel(@RequestBody Aluguel a) {
        log.info("POST /aluguel");
        aluguelService.save(a);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluguel> getAluguel(@PathVariable("id") Long id) {
        log.info("GET /aluguel/{}", id);
        Optional<Aluguel> aluguelOptional = aluguelService.findById(id);
        if (aluguelOptional.isPresent()) {
            return ResponseEntity.ok(aluguelOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("")
    public void putAluguel(@RequestBody Aluguel a) {
        log.info("PUT /aluguel/");
        aluguelService.update(a);
    }

    @DeleteMapping("{id}")
    public void deleteAluguel(@PathVariable("id") Long id) {
        log.info("DELETE /aluguel/");
        aluguelService.deleteById(id);
    }

}
