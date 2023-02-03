package com.tribikebackend.config.security;

import com.tribikebackend.entity.Usuario;
import com.tribikebackend.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Import(UsuarioService.class)
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioService usuarioService;

    private final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user = usuarioService.findByEmail(email);
        if (user == null) {
            log.info("Não foi encontrado o usuário: "+email);
            return null;
        }
        log.info("Encontrado usuário " + user.getEmail());
        return new SecurityUser(user);
    }

}
