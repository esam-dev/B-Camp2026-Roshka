package com.elias.editorial.service;

import com.elias.editorial.dto.AsignaturaDTO;
import com.elias.editorial.dto.CursoDTO;
import com.elias.editorial.model.Asignatura;
import com.elias.editorial.model.Curso;
import com.elias.editorial.service.CursoServiceImpl;

import java.util.List;

public interface AsignaturaService {
    Asignatura guardarDesdeDTO (AsignaturaDTO dto);
    List<Asignatura> listarTodos();
    Asignatura buscarPorId (Long id);
    Asignatura actualizar (Long id ,Asignatura asignatura );
    void eliminar (Long id );
}
