package com.elias.editorial.controller;

import com.elias.editorial.dto.ColegioDTO;
import com.elias.editorial.model.Colegio;
import com.elias.editorial.service.ColegioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colegios")
public class ColegioController {

    private final ColegioService colegioService;

    public ColegioController (ColegioService colegioService){
        this.colegioService = colegioService;
    }

    @PostMapping
    public Colegio crear (@RequestBody ColegioDTO dto){
        return colegioService.guardarDesdeDTO(dto);
    }
    @GetMapping
    public List<Colegio> listar (){
        return  colegioService.listarTodos();
    }
    @GetMapping("/{id}")
    public Colegio buscarPorId (@PathVariable Long id ){
        return colegioService.buscarPorId(id);
    }
    @PutMapping("/{id")
    public Colegio actualizar (@PathVariable Long id , @RequestBody Colegio colegio ){
        return colegioService.actualizar(id, colegio);
    }

    @DeleteMapping("/{id}")
    public void eliminar (@PathVariable Long id ){
        colegioService.eliminar(id);
    }
}
