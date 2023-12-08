package com.example.kiseya.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.kiseya.entitys.Profile;
import com.example.kiseya.entitys.Usuario;
import com.example.kiseya.repository.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private final ProfileRepository profileRepository;
    private final UsuarioService usuarioService;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, UsuarioService usuarioService) {
        this.profileRepository = profileRepository;
        this.usuarioService = usuarioService;
    }

    public Profile getUserProfile(Usuario usuario) {
        Optional<Profile> optionalProfile = profileRepository.findByUsuario(usuario);

        // Se o perfil existir, retorne-o; caso contrário, crie um novo perfil
        return optionalProfile.orElseGet(() -> createProfile(usuario));
    }

    private Profile createProfile(Usuario usuario) {
        // Lógica para criar um novo perfil com valores padrão, se necessário
        Profile novoProfile = new Profile();
        novoProfile.setUsuario(usuario);
        novoProfile.setBio(" "); // Valor padrão para a bio
        novoProfile.setFotoPerfil("/img/whyman.png"); // Caminho para a foto padrão

        // Salvar o novo perfil no repositório
        return profileRepository.save(novoProfile);
    }

    public Profile getProfileByUsuarioId(Long usuarioId) {
        Usuario usuario = usuarioService.getUsuarioById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return profileRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado para o usuário com ID: " + usuarioId));
    }
}

