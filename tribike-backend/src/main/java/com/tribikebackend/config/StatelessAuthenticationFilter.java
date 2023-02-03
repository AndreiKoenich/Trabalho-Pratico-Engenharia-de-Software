/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tribikebackend.config;

import com.tribikebackend.exception.TokenNullException;
import com.tribikebackend.config.security.TokenAuthenticationService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

/**
 *
 * @author igor
 * Filtro para testar se o recebido do usuário é válido
 */
@Component
@Import(TokenAuthenticationService.class)
public class StatelessAuthenticationFilter extends GenericFilterBean {

    @Autowired
    private TokenAuthenticationService authenticationService;

    private final Logger log = LoggerFactory.getLogger(StatelessAuthenticationFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse response = (HttpServletResponse) res;

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, Authorization, Content-Type, "
                + "withCredentials, " + TokenAuthenticationService.AUTH_HEADER_NAME);
        response.setHeader("Access-Control-Expose-Headers", TokenAuthenticationService.AUTH_HEADER_NAME);
        response.setHeader("p3p", "CP=\"This is not a P3P policy!\"");

        if (!httpRequest.getMethod().equals("OPTIONS")) {
            try {
                Authentication authentication = authenticationService.getAuthentication(httpRequest);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (ExpiredJwtException ex) {
                log.info("O token recebido Está expirado!", ex);
                SecurityContextHolder.getContext().setAuthentication(null);
                response.sendError(440, "Token expired. " + ex.getMessage());
                return;
            } catch (SignatureException | MalformedJwtException se) {
                log.info("O token recebido é inválido!", se);
                SecurityContextHolder.getContext().setAuthentication(null);
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Token Invalid. " + se.getMessage());
                return;
            } catch (TokenNullException e) {
                e.printStackTrace();
                SecurityContextHolder.getContext().setAuthentication(null);
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "Token NULL. "+ e.getMessage());
                return;
            }

            filterChain.doFilter(request, res);
        }
        SecurityContextHolder.getContext().setAuthentication(null);
    }

}
