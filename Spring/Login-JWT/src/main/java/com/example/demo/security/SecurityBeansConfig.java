package com.example.demo.security;

// Le marcamos a Spring que esta clase es de configuracion 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Clase que encripta passwords con BCrypt
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBeansConfig {

    @Bean  //Metodo crea un objeto  que Spring va a manejar 
    public PasswordEncoder passwordEncoder () {

        return new BCryptPasswordEncoder() {
        };
    }
}
