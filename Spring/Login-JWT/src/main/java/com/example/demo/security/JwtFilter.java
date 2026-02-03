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
import java.security.Security;

@Component 
public class JwtFilter  extends OncePerRequestFilter {

    private final JwtUtil jwtUtil; // Clase que creamos para manajer JWT (generar, validar, extraer )

    private final UserDetailsService userDetailsService; // Servicio que busca usuarios en la BD 

    // Constructor con inyeccion de dependencias
    public JwtFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService){
        this.jwtUtil = jwtUtil; 
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException{
        
        // Obtiene el header autorizacion del request 
        String authHeader = request.getHeader("Autorizacion");

        // Si no hay header  o no empieza con "Bearer"
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // no hacemos nada y dejamos pasar la request
            filterChain.doFilter(request, response);
            return;
        }

        // Extrae solo el token (quita "Bearer")
        String token = authHeader.substring(7);

        String username;

        try{
            // Extrae el username desde el JWT 
            username = jwtUtil.extraerUsername(token);
        } catch (Exception e ){
            // si el token es invalido o expiro , seguimos sin autenticar 
            filterChain.doFilter(request, response);
            return;
        }

        // verifica que haya username y que aun no este autenticado
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            // Buscamos el usuario en las base de datos 
            UserDetails userDetails = 
                 userDetailsService.loadUserByUsername(username);

           // Valida el token (firma + expiracion)
           if (jwtUtil.validarToken(token)){

            // Crea un objeto de autenticacion 
            UsernamePasswordAuthenticationToken authToken = 
               new UsernamePasswordAuthenticationToken(
                    userDetails,      // usuario
                    null,             // no usamos password esta parte
                    userDetails.getAuthorities()  // roles /permisos 
                );

                // Agregamos detalles de la request (IP, etc)
                authToken.setDetails(
                       new WebAuthenticationDetailsSource()
                       .buildDetails(request)
                );

                // Guardamos la autenticacion en el contexto de seguridad
                SecurityContextHolder
                         .getContext()
                         .setAuthentication(authToken);
           }
        }

        // Continua con el resto del flujo 
        filterChain.doFilter(request, response);
    }

}
