package com.tribikebackend.controller;

import com.tribikebackend.config.security.CustomUserDetailsService;
import com.tribikebackend.config.security.SecurityUser;
import com.tribikebackend.config.security.TokenHandler;
import com.tribikebackend.entity.Usuario;
import com.tribikebackend.entity.dto.LoginUserDto;

import com.tribikebackend.service.UsuarioService;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Objects;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenHandler tokenHandler;

    @Autowired
    private AuthenticationManager authenticationManager;


    private final Logger log = LoggerFactory.getLogger(LoginController.class);

    private CustomUserDetailsService customUserDetailsService;
    LoginController(CustomUserDetailsService customUserDetailsService){
        this.customUserDetailsService = customUserDetailsService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createAuthenticationToken(@RequestBody LoginUserDto authenticationRequest) throws Exception {
        log.info("POST /login " + authenticationRequest.getEmail());
        String userEmail = authenticationRequest.getEmail().toLowerCase();
        SecurityUser securityUser = (SecurityUser) customUserDetailsService.loadUserByUsername(userEmail);

        if (securityUser == null) {
            log.info("Email "+ userEmail +" nao encontrado.");
            String jsonResponse = "{ \"message\":\"Email incorreto.\"}";
            return new ResponseEntity<>(jsonResponse, HttpStatus.FORBIDDEN);
        }

        try{
            authenticate(userEmail, authenticationRequest.getSenha());
            Usuario u = usuarioService.findByEmail(userEmail);
            String token = tokenHandler.createTokenForUser(u);
    
            log.info("Token gerado: "+token);
            String jsonResponse = "{ \"token\":" + "\"" + token + "\"}";
            return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    
        }
        catch(Exception e){
            String jsonResponse;
            if(e.getMessage().contains("Bad credentials")){
                log.info("Senha incorreta");
                jsonResponse = "{ \"message\":\"Senha incorreta.\"}";
            }else{
                log.info(e.getMessage());
                jsonResponse = "{ \"message\":\"Email incorreto.\"}";
            }
                return new ResponseEntity<>(jsonResponse, HttpStatus.FORBIDDEN);
        }
    
    }

    private String authenticate(String email, String password) throws Exception {
        log.info("AUTHENTICATE /login " + email);
        log.info("PASSWORD: "+password);
        Objects.requireNonNull(email);
        Objects.requireNonNull(password);
        String authResponse = "";
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            authResponse = "Usuário logado";
        } catch (Exception e) {
            throw new Exception("Erro ao autenticar usuário ", e);
        }
        return authResponse;
    }

    @RequestMapping(value = "/logged_user", method = RequestMethod.GET)
    public Usuario currentUserRole (Principal principal) {
        log.info("CURRENT USER ROLE /login/logged_user ");
        Usuario p = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario u = usuarioService.findByEmail(p.getEmail());
        System.out.println(u.getUsername());
        return u;
    }

    @RequestMapping(value = "/logged_user", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> currentRole(Principal principal) {
        log.info("CURRENT ROLE /login/logged_user ");

        Usuario p = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario u = usuarioService.findByEmail(p.getEmail());
        
        JSONObject papel = new JSONObject();
        papel.put("papel", u.getPapel());
        return new ResponseEntity<>(papel, HttpStatus.OK);
    }

  /*  public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("senha123"));
    }*/

}
