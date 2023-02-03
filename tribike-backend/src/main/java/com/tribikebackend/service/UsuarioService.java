package com.tribikebackend.service;

import com.tribikebackend.entity.Usuario;
import com.tribikebackend.entity.dto.UsuarioFullDto;
import com.tribikebackend.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    private UsuarioRepository repository;

    UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Optional<Usuario> findById(long id) {
        return repository.findById(id);
    }

    public Usuario findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario save(Usuario u) {
        return repository.save(u);
    }

    public UsuarioFullDto convertToFullDto(Usuario u) {
        UsuarioFullDto dto = new UsuarioFullDto();

        dto.setId(u.getId());

        if (u.getPicture() != null) {
            dto.setPicture(u.getPicture());
        }
        if (u.getName() != null) {
            dto.setName(u.getName());
        }
        if (u.getEmail() != null) {
            dto.setEmail(u.getEmail());
        }
        if (u.getPasswordHash() != null) {
            dto.setPasswordHash(u.getPasswordHash());
        }
        return dto;
    }
}
