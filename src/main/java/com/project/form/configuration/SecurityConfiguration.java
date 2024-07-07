package com.project.form.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {


    // CONFIGURACOES DE SEGURACA BASICO
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/form", "/login").permitAll() // URL PUBLICAS
                                .requestMatchers("/admin", "/admin/**").hasRole("ADMIN") // URL ADMIN
                ).formLogin(login ->
                        login
                                .loginPage("/login") // TELA DE LOGIN
                                .loginProcessingUrl("/login") // ONDE OS DADOS DE LOGIN SERAO PROCESSADOS
                                .defaultSuccessUrl("/admin") // REDIRECIONAMENTO CASO LOGIN ESTEJA CORRET
                                .permitAll()
                ).logout(logout ->
                        logout
                                .logoutUrl("/logout") // URL DE LOGOUT
                                .logoutSuccessUrl("/form")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                                .permitAll())
                .sessionManagement(session ->
                        session
                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // CRIA TIPO DE SESSAO
                                .invalidSessionUrl("/login?expired") // URL PARA SESSAO EXPIRADA
                                .sessionFixation().migrateSession() // EVITAR ATAQUES DE FIXACAO DE SESSAO
                                .maximumSessions(1) // NUMERO MAXIMO DE PESSOAS LOGADAS AO MSM TEMPO
                                .maxSessionsPreventsLogin(false) // NAO PERMITE LOGINS ALEM DO LIMITE
                                .maximumSessions(1).expiredUrl("/login"));

        return http.build();
    }

    // CRIA UMA INSTANCIA DO AUTHENTICATION MANAGER PERSONALIZADO
    @Bean
    public AuthenticationManager authManager(HttpSecurity http, AuthenticationProvider authenticationProvider) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);

        return authenticationManagerBuilder.build();
    }
}
