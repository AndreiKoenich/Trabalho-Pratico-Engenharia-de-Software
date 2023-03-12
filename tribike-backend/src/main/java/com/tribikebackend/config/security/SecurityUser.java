/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tribikebackend.config.security;

import com.tribikebackend.entity.Usuario;
import com.tribikebackend.entity.dto.LoginUserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SecurityUser extends LoginUserDto implements UserDetails {

    private boolean enabled;
    private List<String> roles;

    private final Logger log = LoggerFactory.getLogger(SecurityUser.class);

    public SecurityUser(Usuario u) {
        super(u.getEmail(), u.getPasswordHash());
        this.roles = Collections.singletonList(u.getPapelString());
        System.out.println(this.roles);
        this.enabled = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        for (String s : this.roles) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(s);
            authorities.add(authority);
        }
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return this.getPassword();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isEnabled() {
        //return this.enabled;
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
       // return this.enabled;
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
//        return this.enabled;
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
//        return this.enabled;
        return true;


    }

    public List<String> getRoles() {
        return roles;
    }

}
