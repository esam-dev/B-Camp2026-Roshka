package com.elias.editorial.controller;

import com.elias.editorial.dto.PrestamoDTO;
import com.elias.editorial.model.Prestamo;
import com.elias.editorial.service.PrestamoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    //  CREAR
    @PostMapping
    public Prestamo crear(@RequestBody PrestamoDTO dto ) {
        return prestamoService.guardarDesdeDTO(dto);
    }

    //  LISTAR TODOS
    @GetMapping
    public List<Prestamo> listar() {
        return prestamoService.listarTodos();
    }

    //  BUSCAR POR ID
    @GetMapping("/{id}")
    public Prestamo buscarPorId(@PathVariable Long id) {
        return prestamoService.buscarPorId(id);
    }

    //  ACTUALIZAR
    @PutMapping("/{id}")
    public Prestamo actualizar(
            @PathVariable Long id,
            @RequestBody Prestamo prestamo) {

        return prestamoService.actualizar(id, prestamo);
    }

    //  ELIMINAR
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        prestamoService.eliminar(id);
    }

}

