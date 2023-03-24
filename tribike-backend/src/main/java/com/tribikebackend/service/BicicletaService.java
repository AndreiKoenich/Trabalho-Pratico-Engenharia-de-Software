package com.tribikebackend.service;

import com.tribikebackend.entity.Bicicleta;
import com.tribikebackend.entity.Usuario;
import com.tribikebackend.entity.dto.BicicletaDto;
import com.tribikebackend.entity.dto.NewBicicletaDto;
import com.tribikebackend.repository.BicicletaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BicicletaService {
    private static final Logger log = LoggerFactory.getLogger(BicicletaService.class);

    @Autowired
    private BicicletaRepository bicicletaRepository;
    @Autowired
    private UsuarioService usuarioService;

    public List<BicicletaDto> findAll() {
        return bicicletaRepository.findAll().stream().map(BicicletaDto::new).collect(Collectors.toList());
    }
    public List<BicicletaDto> findAllByUsuarioId(Long id) {
        return bicicletaRepository.findAllByUsuarioId(id).stream().map(BicicletaDto::new).collect(Collectors.toList());
    /*    Optional<Usuario> usuario = usuarioService.findById(id);
        if (usuario.isPresent()) {
            return bicicletaRepository.findAllbyDono(usuario.get());
        } else {
            return new ArrayList<>();
        } */
    }
    public Bicicleta save(NewBicicletaDto b) {
        Bicicleta bicicleta = Bicicleta.builder()
                                    .marca(b.getMarca())
                                    .modelo(b.getModelo())
                                    .descricao(b.getDescricao())
                                    .preco(b.getPreco())
                                    .urlFoto(b.getUrlFoto())
                                    .peso(b.getPeso())
                                    .acessorios(b.getAcessorios())
                                    .desconto(b.getDesconto())
                                    .bairro(b.getBairro())
                                    .build();
        if (b.getUsuario() != null) {
            Optional<Usuario> usuarioOptional = usuarioService.findById(b.getUsuario());
            if (usuarioOptional.isPresent()) {
                bicicleta.setUsuario(usuarioOptional.get());
            }
        }
        return bicicletaRepository.save(bicicleta);
    }

    public BicicletaDto findById(Long id) {
        Optional<Bicicleta> bicicletaOptional = bicicletaRepository.findById(id);
        if (bicicletaOptional.isPresent()) {
            return new BicicletaDto(bicicletaOptional.get());
        } else {
            return null;
        }
    }

    public void update(Bicicleta b) {
        bicicletaRepository.save(b);
    }

    public void deleteById(Long id) {
        bicicletaRepository.deleteById(id);
    }
}
