package com.example.kiseya.controllers;

//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.kiseya.entitys.Usuario;
import com.example.kiseya.requests.UsuarioLoginRequest;
import com.example.kiseya.security.TokenUtil;
import com.example.kiseya.services.UsuarioService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.salvar(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @CrossOrigin(origins ="*")
    public ResponseEntity<Map<String, String>> realizarLogin(@RequestBody UsuarioLoginRequest usuarioLoginRequest) {
    Optional<Usuario> usuarioOptional = usuarioService.autenticarUsuario(usuarioLoginRequest.getEmail(), usuarioLoginRequest.getSenha());

        System.out.println("Body da solicitação: " + usuarioLoginRequest.toString());
        if (usuarioOptional.isPresent()) {
            // Usuário autenticado com sucesso
            Usuario usuario = usuarioOptional.get();

            // Crie e envie um token
            String token = TokenUtil.getToken(usuario.getEmail()); 

            // Construa a resposta com o token e a URL de redirecionamento
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("redirectUrl", "/dashboard"); 

            return ResponseEntity.ok(response);
        } else {
            // Autenticação falhou
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/userlogado")
    public ResponseEntity<Usuario> getUsuarioLogado() { 
        Usuario usuarioLogado = usuarioService.getUsuarioLogado();
        return ResponseEntity.ok(usuarioLogado);
    }
}
