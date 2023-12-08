package com.example.kiseya.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.kiseya.services.UsuarioService;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = false)
public class SecurityConfig {

    @Autowired
    private AuthenticationConfiguration configuration;

    @Autowired
    private UsuarioService usuarioService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.configure(http))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        authorizeConfig -> {
                            authorizeConfig.requestMatchers(HttpMethod.POST, "/login").permitAll();
                            authorizeConfig.requestMatchers(HttpMethod.GET, "/login").permitAll();
                            authorizeConfig.requestMatchers(HttpMethod.POST, "/home ").permitAll();
                            authorizeConfig.requestMatchers(HttpMethod.GET, "/registrar").permitAll();
                            authorizeConfig.requestMatchers(HttpMethod.POST, "/api/usuarios/cadastrar").permitAll();
                            authorizeConfig.requestMatchers(HttpMethod.POST, "/api/usuarios/login").permitAll();
                            authorizeConfig.requestMatchers(HttpMethod.GET, "/dashboard").authenticated();
                            authorizeConfig.anyRequest().permitAll();
                        })
                .addFilter(new JWTAuthenticationFilter(configuration.getAuthenticationManager()))
                .addFilter(new JWTAuthorizationFilter(configuration.getAuthenticationManager(), usuarioService))
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(List.of("http://localhost:4200", "http://localhost:8080"));
        configuration.setExposedHeaders(Arrays.asList("fresh-token"));
        configuration.setAllowedHeaders(Arrays.asList("content-type", "authorization"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

