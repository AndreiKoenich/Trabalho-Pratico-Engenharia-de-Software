package com.tribikebackend.service;

import com.tribikebackend.entity.Aluguel;
import com.tribikebackend.entity.Bicicleta;
import com.tribikebackend.entity.Usuario;
import com.tribikebackend.entity.dto.AluguelDto;
import com.tribikebackend.entity.dto.NewAluguelDto;
import com.tribikebackend.repository.AluguelRepository;
import com.tribikebackend.repository.BicicletaRepository;
import com.tribikebackend.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AluguelService {
    private static final Logger log = LoggerFactory.getLogger(AluguelService.class);

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private BicicletaRepository bicicletaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<AluguelDto> findAll() {
        return aluguelRepository.findAll().stream().map(AluguelDto::new).collect(Collectors.toList());
    }

    public Aluguel save(NewAluguelDto a) {
        Aluguel aluguel = Aluguel.builder()
                .dataInicio(a.getDataInicio())
                .dataFim(a.getDataFim())
                .precoTotal(a.getPrecoTotal())
                .build();
        if (a.getBicicleta() != null) {
            Optional<Bicicleta> bicicletaOptional = bicicletaRepository.findById(a.getBicicleta());
            if (bicicletaOptional.isPresent()) {
                Bicicleta bicicleta = bicicletaOptional.get();
                aluguel.setBicicleta(bicicleta);
                if (bicicleta.getUsuario() != null) {
                    aluguel.setLocador(bicicleta.getUsuario());
                }
            }
        }
        if (a.getLocatario() != null) {
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(a.getLocatario());
            if (usuarioOptional.isPresent()) {
                aluguel.setLocatario(usuarioOptional.get());
            }
        }
        return aluguelRepository.save(aluguel);
    }

    public AluguelDto findById(Long id) {
        Optional<Aluguel> aluguelOptional = aluguelRepository.findById(id);
        if (aluguelOptional.isPresent()) {
            return new AluguelDto(aluguelOptional.get());
        } else {
            return null;
        }
    }

    public void update(Aluguel a) {
        aluguelRepository.save(a);
    }

    public void deleteById(Long id) {
        aluguelRepository.deleteById(id);
    }

}
