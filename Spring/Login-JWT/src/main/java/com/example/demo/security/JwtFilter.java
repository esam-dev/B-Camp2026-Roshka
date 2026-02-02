package com.example.demo.security;

import jakarta.servlet.FilterChain;

//  Excepción general de servlets
import jakarta.servlet.ServletException;    

import jakarta.servlet.http.HttpServletRequest;      // Representa la request HTTP que llega (headers, body, etc)
import jakarta.servlet.http.HttpServletResponse;     // Representa la response HTTP que se va a devolver
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

// Guarda la info del usuario autenticado en el contexto de seguridad
import org.springframework.security.core.context.SecurityContextHolder;        
import org.springframework.security.core.userdetails.UserDetails;     // Representa los datos del usuario (username, password, roles)
import org.springframework.security.core.userdetails.UserDetailsService;    // Servicio para buscar usuarios por username 
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;  //Agrega detalles extra de la request (IP, sesión, etc)
import org.springframework.stereotype.Component;    // Marca esta clase como un Bean de Spring
import org.springframework.web.filter.OncePerRequestFilter;   // Filtro que se ejecuta UNA sola vez por request
import java.io.IOException;   // Excepción de entrada/salida

@Component 
public class JwtFilter {

}
