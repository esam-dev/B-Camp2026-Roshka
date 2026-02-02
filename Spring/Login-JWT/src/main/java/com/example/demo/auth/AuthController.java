package com.example.demo.auth;


import com.example.demo.security.JwtUtil;
import com.example.demo.usuario.UsuarioService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UsuarioService usuarioService;

    public AuthController(AuthenticationManager authManager,
            JwtUtil jwtUtil,
            UsuarioService usuarioService) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    // LOGIN 
    @PostMapping("/login")
    public String login (@RequestBody LoginRequest request){

         try {
            authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
                )
            );
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Usuario o contraseña incorrectos");
        }

        // Genera JWT
        return jwtUtil.generarToken(request.getUsername());
    }
    
    // REGISTRO 
    @PostMapping("/register")
    public String registro (@RequestBody RegisterRequest request) {
        try {
            usuarioService.registrar(request.getUsername(), request.getPassword());
            return "Usuario creado correctamente";
        } catch (RuntimeException e) {
            return e.getMessage(); // "Usuario ya existe"
        }
    }
}
