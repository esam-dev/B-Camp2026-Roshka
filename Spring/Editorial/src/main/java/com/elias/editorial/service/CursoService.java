package com.elias.editorial.service;

import com.elias.editorial.dto.CursoDTO;
import com.elias.editorial.dto.CursoDTO;
import com.elias.editorial.model.Curso;

import java.util.List;

public interface CursoService {
    Curso guardarDesdeDTO (CursoDTO dto);
    List<Curso> listarTodos();
    Curso buscarPorId (Long id);
    Curso actualizar (Long id , Curso curso );
    void eliminar (Long id );
}

