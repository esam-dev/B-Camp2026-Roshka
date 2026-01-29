package com.elias.editorial.service;

import com.elias.editorial.dto.LibroDTO;
import com.elias.editorial.model.Libro;
import java.util.List;

public interface LibroService {
    Libro guardarDesdeDTO(LibroDTO dto);
    List<Libro> listarTodos();
    Libro buscarPorId (Long id );
    Libro actualizar (Long id , Libro libro);
    void eliminar (Long id );
}
