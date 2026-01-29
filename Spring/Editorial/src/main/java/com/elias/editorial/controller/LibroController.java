package com.elias.editorial.controller;

import com.elias.editorial.dto.LibroDTO;
import com.elias.editorial.model.Libro;
import com.elias.editorial.service.LibroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController (LibroService libroService){
        this.libroService = libroService;
    }

    @PostMapping
    public Libro crear (@RequestBody LibroService LibroDTO dto ){
        return libroService.guardarDesdeDTO(dto);
    }

    @GetMapping
    public List<Libro> listar (){
        return libroService.listarTodos();
    }

    @GetMapping("/{id}")
    public Libro buscarPorId (@PathVariable Long id){
        return buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Libro actualizar (@PathVariable Long id ,@RequestBody Libro libro ){
        return libroService.actualizar(id,libro);
    }

    @DeleteMapping("/{id}")
    public void eliminar (@PathVariable Long id ){
        libroService.eliminar(id);
    }

}


