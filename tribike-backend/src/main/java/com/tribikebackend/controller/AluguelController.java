package com.tribikebackend.controller;

import com.tribikebackend.entity.Aluguel;
import com.tribikebackend.entity.dto.AluguelDto;
import com.tribikebackend.entity.dto.NewAluguelDto;
import com.tribikebackend.service.AluguelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluguel")
public class AluguelController {
    private static final Logger log = LoggerFactory.getLogger(AluguelController.class);

    @Autowired
    private AluguelService aluguelService;

    @GetMapping("")
    public List<AluguelDto> getAlugueis(
            @RequestParam(value = "usuario", required = false) Long userId
    ) {
        log.info("GET /aluguel");
        if (userId != null) {
            List<AluguelDto> output = aluguelService.findAllByLocatarioId(userId);
            log.info("{} alugueis encontrados", output.size());
            return output;
        } else {
            List<AluguelDto> output = aluguelService.findAll();
            log.info("{} alugueis encontrados", output.size());
            return output;
        }
    }

    @PostMapping("")
    public ResponseEntity<AluguelDto> postAluguel(@RequestBody NewAluguelDto aluguel) {
        log.info("POST /aluguel");
        Aluguel a = aluguelService.save(aluguel);
        return ResponseEntity.ok(new AluguelDto(a));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AluguelDto> getAluguel(@PathVariable("id") Long id) {
        log.info("GET /aluguel/{}", id);
        AluguelDto aluguel = aluguelService.findById(id);
        if (aluguel != null) {
            return ResponseEntity.ok(aluguel);
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
