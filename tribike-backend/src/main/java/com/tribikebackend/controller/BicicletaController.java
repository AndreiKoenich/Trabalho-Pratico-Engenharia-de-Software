package com.tribikebackend.controller;

import com.tribikebackend.entity.Bicicleta;
import com.tribikebackend.entity.dto.BicicletaDto;
import com.tribikebackend.entity.dto.NewBicicletaDto;
import com.tribikebackend.service.BicicletaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bicicleta")
public class BicicletaController {
    private static final Logger log = LoggerFactory.getLogger(BicicletaController.class);

    @Autowired
    private BicicletaService bicicletaService;

    @GetMapping("")
    public List<BicicletaDto> getBicicletas(
            @RequestParam(value = "usuario", required = false) Long userId) {
        log.info("GET /bicicleta");
        if (userId != null) {
            List<BicicletaDto> output = bicicletaService.findAllByUsuarioId(userId);
            log.info("{} bicicletas encontradas", output.size());
            return output;
        } else {
            List<BicicletaDto> output = bicicletaService.findAll();
            log.info("{} bicicletas encontradas", output.size());
            return output;
        }
    }

    @PostMapping("")
    public ResponseEntity<BicicletaDto> postBicicleta(@RequestBody NewBicicletaDto bicicleta) {
        log.info("POST /bicicleta");
        Bicicleta b = bicicletaService.save(bicicleta);
        return ResponseEntity.ok(new BicicletaDto(b));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BicicletaDto> getBicicleta(@PathVariable("id") Long id) {
        log.info("GET /bicicleta/{}", id);
        BicicletaDto bicicleta = bicicletaService.findById(id);
        if (bicicleta != null) {
            return ResponseEntity.ok(bicicleta);
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
