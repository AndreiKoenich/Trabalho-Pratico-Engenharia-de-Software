package com.tribikebackend.controller;

import com.tribikebackend.entity.Usuario;
import com.tribikebackend.entity.dto.NewUsuarioDto;
import com.tribikebackend.entity.dto.UsuarioFullDto;
import com.tribikebackend.entity.dto.UsuarioMiniDto;
import com.tribikebackend.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    private UsuarioService service;

    @GetMapping("")
    public List<UsuarioFullDto> getAll() {
        log.info("GET all Usuario");
        List<UsuarioFullDto> dtoList = new ArrayList<>();
        for (Usuario entity : service.findAll()) {
            dtoList.add(service.convertToFullDto(entity));
        }
        return dtoList;
    }

    @GetMapping("/logado")
    public ResponseEntity<UsuarioMiniDto> getAuthenticatedUser() {
        log.info("GET usuario");
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuarioCompleto = service.findByEmail(usuario.getEmail());

        return ResponseEntity.ok().body(new UsuarioMiniDto(usuarioCompleto));
    }

    @PostMapping("")
    public Usuario saveUsuario(@RequestBody NewUsuarioDto usuario) {
        log.info("POST /user");
        log.info("Usuario: {}", usuario.getUsername());
        log.info("Usuario: {}", usuario.getEmail());
        return service.save(usuario);
    }
}
