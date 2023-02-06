package com.tribikebackend.service;

import com.tribikebackend.entity.Usuario;
import com.tribikebackend.entity.dto.NewUsuarioDto;
import com.tribikebackend.entity.dto.UsuarioFullDto;
import com.tribikebackend.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public Usuario save(NewUsuarioDto newUsuario) {
        Usuario usuario = new Usuario();
        usuario.setEmail(newUsuario.getEmail());
        usuario.setUsername(newUsuario.getUsername());
        String passwordHash = new BCryptPasswordEncoder().encode(newUsuario.getPassword());
        usuario.setPasswordHash(passwordHash);
        usuario.setCpf(newUsuario.getCpf());
        usuario.setAgenciaBancaria(newUsuario.getAgenciaBancaria());
        usuario.setContaBancaria(newUsuario.getContaBancaria());
        usuario.setEndereco(newUsuario.getEndereco());
        usuario.setDataNascimento(newUsuario.getDataNascimento());
        if (newUsuario.getPapel() != null) {
            usuario.setPapel(newUsuario.getPapel());
        } else {
            usuario.setPapel(2);
        }
        return repository.save(usuario);
    }


    public UsuarioFullDto convertToFullDto(Usuario u) {
        UsuarioFullDto dto = new UsuarioFullDto();

        dto.setId(u.getId());

        if (u.getUsername() != null) {
            dto.setName(u.getUsername());
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
