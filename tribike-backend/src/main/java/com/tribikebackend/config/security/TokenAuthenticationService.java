/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tribikebackend.config.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

import com.tribikebackend.entity.Usuario;
import com.tribikebackend.exception.TokenNullException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationService {

    public static final String AUTH_HEADER_NAME = "Authorization";

    @Autowired
    private TokenHandler tokenHandler;
     private final Logger log = LoggerFactory.getLogger(TokenAuthenticationService.class);

    public void addAuthentication(HttpServletResponse response, Authentication authentication) {
        final Usuario user = (Usuario) authentication.getPrincipal();
        response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
    }

    public Authentication getAuthentication(HttpServletRequest request) throws TokenNullException{
        final String token = request.getHeader(AUTH_HEADER_NAME);
        
        if (isNotBlank(token)) {
            log.info("token:" + token);
            final Usuario user = tokenHandler.parseUserFromToken(token);
            log.debug("criou user");
            if (user != null) {
                SecurityUser secUser = new SecurityUser(user);
                Authentication auth = new UsernamePasswordAuthenticationToken(user, null, secUser.getAuthorities());
                return auth;
            }
        }
        else{
       /*     if(!String.valueOf(request.getRequestURI()).equals("/login")){
                throw new TokenNullException(
                        "Current token is: NULL");
            } */
        }
        return null;
    }
}
