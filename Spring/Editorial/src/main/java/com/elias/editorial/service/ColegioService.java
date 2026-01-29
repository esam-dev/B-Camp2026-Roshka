package com.elias.editorial.service;

import com.elias.editorial.model.Colegio;
import com.elias.editorial.dto.ColegioDTO;

import java.util.List;

public interface ColegioService {
    Colegio  guardarDesdeDTO (ColegioDTO dto);
    List<Colegio> listarTodos();
    Colegio buscarPorId (Long id);
    Colegio actualizar (Long id ,Colegio colegio );
    void eliminar (Long id );
}
