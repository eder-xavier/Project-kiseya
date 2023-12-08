package com.example.kiseya.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kiseya.entitys.Profile;
import com.example.kiseya.entitys.Usuario;
import com.example.kiseya.services.ProfileService;
import com.example.kiseya.services.UsuarioService;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuarios/profile")
public class ProfileController {

    private final UsuarioService usuarioService;
    private final ProfileService profileService;

    @Autowired
    public ProfileController(UsuarioService usuarioService, ProfileService profileService) {
        this.usuarioService = usuarioService;
        this.profileService = profileService;
    }

    @GetMapping("/me")
    public ResponseEntity<Profile> getMyProfile() {
        Long usuarioId = getLoggedUserId();
        Profile profile = profileService.getProfileByUsuarioId(usuarioId);
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/me/current")
    public ResponseEntity<?> getCurrentUserProfile() {
        Long usuarioId = getLoggedUserId();
        Optional<Usuario> optionalUsuario = usuarioService.getUsuarioById(usuarioId);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            // Agora você pode usar o objeto Usuario normalmente
            Profile userProfile = profileService.getUserProfile(usuario);
            return ResponseEntity.ok(userProfile);
        } else {
            // Lida com o caso em que o usuário não foi encontrado pelo ID
            return ResponseEntity.notFound().build();
        }
    }
    // Adicione aqui outros endpoints conforme necessário

    private Long getLoggedUserId() {
        String usuarioEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.getUsuarioPorEmail(usuarioEmail);
        return usuario.getId();
    }
}