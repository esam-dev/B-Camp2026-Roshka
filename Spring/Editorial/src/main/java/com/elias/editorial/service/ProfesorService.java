package com.elias.editorial.service;

import com.elias.editorial.dto.ProfesorDTO;
import com.elias.editorial.model.Profesor;
import java.util.List;

public interface ProfesorService {

    Profesor guardarDesdeDTO(ProfesorDTO dto);
    List<Profesor> listarTodos();
    Profesor buscarPorId(Long id);
    Profesor actualizar(Long id, Profesor profesor);
    void eliminar(Long id);
}
