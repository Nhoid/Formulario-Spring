package com.project.form.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// AUTHENTICACAO PERSONALIZADA
// AUTHENTICACAO DE ADMIN SIMPLES
@Component
public class CustomAuthentication implements AuthenticationProvider {


    @Value("${spring.admin.username}")
    private String username;

    @Value("${spring.admin.password}")
    private String password;


    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        String username = authentication.getName(); // PEGA O USERNAME FORNECIDO
        String password = authentication.getCredentials().toString(); // PEGA A SENHA FORNECIDA

        if (username.equals(this.username) && password.equals(this.password)) { // COMPARA
            return authenticateAgainstThirdPartyAndGetAuthentication(username, password); //RETORNA USUARIO
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


    private Authentication authenticateAgainstThirdPartyAndGetAuthentication(final String username, final String password) {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        final UserDetails userDetails = new User(username, password, authorities);
        return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);

    }
}
