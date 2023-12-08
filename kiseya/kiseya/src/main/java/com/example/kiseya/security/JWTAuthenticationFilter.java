package com.example.kiseya.security;

import java.io.IOException;
//import java.util.Collections;
import java.util.Collections;

import org.apache.catalina.connector.Response;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//import com.example.kiseya.security.JWTAuthorizationFilter;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.kiseya.requests.UsuarioLoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;




public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
/*
    @Override
public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
        throws AuthenticationException {
    try {
        String email = obtainUsername(request);
        String senha = obtainPassword(request);

        if (email == null) {
            email = "";
        }

        if (senha == null) {
            senha = "";
        }

        email = email.trim();

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        usuario.getEmail(),
                        usuario.getSenha()));
    } catch (RuntimeException e) {
        throw new RuntimeException("Erro ao processar a requisição de autenticação", e);
    }
}
 */
  @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            // Obtém as credenciais do corpo da solicitação
            UsuarioLoginRequest creds = new ObjectMapper().readValue(request.getInputStream(), UsuarioLoginRequest.class);
            
            // Cria um token de autenticação
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    creds.getEmail(), creds.getSenha(), Collections.emptyList());

            // Retorna a autenticação para o provedor
            return authenticationManager.authenticate(authToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      Authentication auth) throws IOException, ServletException {

    response.setHeader("Content-Type", "application/json; charset=UTF-8");
    response.setCharacterEncoding("UTF-8");

    UsernamePasswordAuthenticationToken authentication = JWTAuthorizationFilter.getAuthentication(request, response);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    try {

      String email = ((User)auth.getPrincipal()).getUsername();


      String token = TokenUtil.getToken(email);
      String json = String.format("{\"token\": \"%s\" }", token);
      response.getWriter().write(json);

    } catch (Exception e) {
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      String json = String.format("{ \"msg\" : \"%s\" }", e.getMessage());
      response.getWriter().write(json);
    }
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException failed) throws IOException, ServletException {
    response.setHeader("Content-Type", "application/json; charset=UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.setStatus(Response.SC_UNAUTHORIZED);
    String json = "{ \"msg\" : \"Erro: login ou senha inválidos\" }";
    response.getWriter().write(json);
  }
}
