package com.elias.editorial.service;

import com.elias.editorial.dto.PrestamoDTO;
import com.elias.editorial.model.Prestamo;
import java.util.List;

public interface PrestamoService {

    Prestamo guardarDesdeDTO (PrestamoDTO dto);
    List<Prestamo> listarTodos();
    Prestamo buscarPorId(Long id);
    Prestamo actualizar (Long id, Prestamo prestamo);
    void eliminar(Long id);
}
