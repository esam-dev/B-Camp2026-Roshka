package com.example.demo.security;

import io.jsonwebtoken.*;                                                               // JJWT core
import io.jsonwebtoken.security.Keys;                                                   // para Key desde bytes
import org.springframework.stereotype.Component;                                       // bean Spring
import java.nio.charset.StandardCharsets;                                               // UTF-8 charset
import java.security.Key;                                                               // tipo Key
import java.util.Base64;                                                                // decode base64
import java.util.Date;                                                                  // fechas

@Component
public class JwtUtil {

    // Clave secreta 
    private static final String SECRET_KEY = " ";

    // Una hora de validez
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 ; 

    private Key  getKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // Genera el token 
    public String generarToken (String username ){
        return Jwts.builder()
                .setSubject(username)               // usuario
                .setIssuedAt(new Date())            // fecha creación
                .setExpiration(
                        new Date(System.currentTimeMillis() + EXPIRATION_TIME)
                )
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extrae username desde el token 
    public String extraerUsername (String token ){

        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
