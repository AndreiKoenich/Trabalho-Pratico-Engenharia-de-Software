package com.tribikebackend.controller;

import com.tribikebackend.entity.Bicicleta;
import com.tribikebackend.service.BicicletaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bicicleta")
public class BicicletaController {
    private static final Logger log = LoggerFactory.getLogger(BicicletaController.class);

    @Autowired
    private BicicletaService bicicletaService;

    @GetMapping("")
    public List<Bicicleta> getBicicletas() {
        log.info("GET /bicicleta");
        List<Bicicleta> output = bicicletaService.findAll();
        log.info("{} bicicletas encontradas", output.size());
        return output;
    }

    @PostMapping("")
    public void postBicicleta(@RequestBody Bicicleta bicicleta) {
        log.info("POST /bicicleta");
        bicicletaService.save(bicicleta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bicicleta> getBicicleta(@PathVariable("id") Long id) {
        log.info("GET /bicicleta/{}", id);
        Optional<Bicicleta> bicicletaOptional = bicicletaService.findById(id);
        if (bicicletaOptional.isPresent()) {
            return ResponseEntity.ok(bicicletaOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("")
    public void putBicicleta(@RequestBody Bicicleta bicicleta) {
        log.info("PUT /bicicleta/");
        bicicletaService.update(bicicleta);
    }

    @DeleteMapping("{id}")
    public void deleteBicicleta(@PathVariable("id") Long id) {
        log.info("DELETE /bicicleta/");
        bicicletaService.deleteById(id);
    }
}
