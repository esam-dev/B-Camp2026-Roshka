package com.example.demo.usuario;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;
    private final PasswordEncoder encoder;

    // Constructor 
    public UsuarioService(UsuarioRepository repo, PasswordEncoder encoder){
        this.repo = repo;
        this.encoder = encoder;     
    }
    // Crear usuario (register)
    public void registrar (String Usuario, String password){

        // Evitar duplicados 
        if (repo.findByUsername(username).isPresent()){
            throw new RuntimeException("Usuario ya existe");
        }

        Usuario u = new Usuario();
        u.setUsername(username);
        u.setPassword(encoder.encode(password)); // Encripta 
        u.setEnabled(true);
    }
}
