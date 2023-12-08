package com.example.kiseya.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.kiseya.entitys.Usuario;
import com.example.kiseya.repository.UsuarioRepository;


@Service
public class UsuarioService {

    private static final String PASSWORD_REGEX = "^(?=.*[a-zA-Z]).{6,}$";

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Usuario getUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario salvar(Usuario usuario) throws RuntimeException {
        String senhaNormal = usuario.getSenha();
        String senhaCriptografada = bCryptPasswordEncoder.encode(senhaNormal);
        usuario.setSenha(senhaCriptografada);

        Usuario usr = usuarioRepository.findByEmail(usuario.getEmail());
        if (usr != null) {
            throw new RuntimeException("Login já existe");
        }

        if (!senhaAtendeRequisitos(senhaNormal)) {
            throw new RuntimeException("Senha não atende os requisitos");
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void remover(Integer idUsuario) {
        usuarioRepository.deleteById(idUsuario.longValue());
    }

    private boolean senhaAtendeRequisitos(String senha) {
        return senha.matches(PASSWORD_REGEX);
    }

    public Optional<Usuario> autenticarUsuario(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        
        if (usuario != null && bCryptPasswordEncoder.matches(senha, usuario.getSenha())) {
            return Optional.of(usuario);
        } else {
            return Optional.empty();
        }
    }
    
    
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario getUsuarioLogado() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null || !authentication.isAuthenticated()) {
        throw new RuntimeException("Nenhum usuário autenticado");
    }

    return (Usuario) authentication.getPrincipal();
}

public Optional<Usuario> obterUsuarioPorId(Long id) {
    return usuarioRepository.findById(id);
}

      
}
