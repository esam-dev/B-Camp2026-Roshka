package com.example.demo.security;

import io.jsonwebtoken.*;                                                               // JJWT core
import io.jsonwebtoken.security.Keys;                                                   // para Key desde bytes
import org.springframework.stereotype.Component;                                       // bean Spring
import java.nio.charset.StandardCharsets;                                               // UTF-8 charset
import java.security.Key;                                                               // tipo Key
import java.util.Date;                                                                  // fechas

@Component
public class JwtUtil {

    // Una hora de validez
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 ; 
    // Clave secreta 
    private static final String SECRET = 
          System.getenv("JWT_SECRET") != null
                 ? System.getenv("JWT_SECRET")
                 : "clave_secreta_dev_no_usar_en_prod_123456";

      // Clave final usada para firmar y validar
    private final Key key = Keys.hmacShaKeyFor(
        SECRET.getBytes(StandardCharsets.UTF_8)
      );

    // Genera el token 
    public String generarToken (String username ){
        return Jwts.builder()
                .setSubject(username)               // usuario
                .setIssuedAt(new Date())            // fecha creación
                .setExpiration(
                        new Date(System.currentTimeMillis() + EXPIRATION_TIME)
                )
                .signWith(getKey(), SignatureAlgorithm.HS256)  // firma 
                .compact();
    }

    // Extrae username del token 
    public String extraerUsername (String token ){

        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)      // Valida firma y expiracion 
                .getBody()
                .getSubject();
    }

    // Valida token (True / false)
    public boolean validarToken (String token ){
        try{
            extraerUsername(token);
            return true;
        } catch(JwtException e ){   
            return false;
        }
    }
}
