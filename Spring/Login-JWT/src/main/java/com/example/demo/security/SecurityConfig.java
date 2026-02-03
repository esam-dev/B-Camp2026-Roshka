package com.example.demo.security;

import org.springframework.context.annotation.Bean; // Para registrar beans manualmente 
import org.springframework.context.annotation.Configuration; // Maneja esta clase como configuracion
import org.springframework.security.authentication.AuthenticationManager;
// Provee el AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity; // para configurar seguridad HTTP
import org.springframework.security.config.http.SessionCreationPolicy; // Define cómo manejar sesiones
import org.springframework.security.web.SecurityFilterChain; // Cadena de filtros de Spring Security
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter; // Filtro de login por defecto (lo reemplazamos con JWT)

@Configuration
// Clase que configura la seguridad de Spring 
public class SecurityConfig {

    private final JwtFilter jwtFilter; // filtro JWT

    public SecuriryConfig(JwtFilter jwtFilter){
        this.jwtFilter = jwtFilter ; 
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        // Desactiva CSRF porque no usamos sesiones de formularios
        .csrf(csrf -> csrf.disable())

        // JWT = stateless → no usamos sesiones
        .sessionManagement(session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )

        // Configura que rutas son publicas y cuales protegidas 
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/auth/**").permitAll() // login y registros publicos 
            .anyRequest().authenticated() // todo lo demas necesita JWT 
        )

        // Ponemos nuestro filtro JWT antes del filtro de Spring
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); 
        return http.build(); // construye la cadena de filtros 

        @Bean
        public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
        ) throws Exception{
            // bean permite  que AuthController use AuthenticationManager
            return config.getAuthenticationManager();
        }

    }
}
