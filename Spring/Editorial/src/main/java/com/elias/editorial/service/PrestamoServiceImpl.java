package com.elias.editorial.service;

import com.elias.editorial.dto.PrestamoDTO;
import com.elias.editorial.model.Prestamo;
import com.elias.editorial.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
 public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final ProfesorRepository profesorRepository;
    private final LibroRepository libroRepository;
    private final AsignaturaRepository asignaturaRepository;
    private final CursoRepository cursoRepository;

    public PrestamoServiceImpl(
            PrestamoRepository prestamoRepository,
            ProfesorRepository profesorRepository,
            LibroRepository libroRepository,
            AsignaturaRepository asignaturaRepository,
            CursoRepository cursoRepository) {

        this.prestamoRepository = prestamoRepository;
        this.profesorRepository = profesorRepository;
        this.libroRepository = libroRepository;
        this.asignaturaRepository = asignaturaRepository;
        this.cursoRepository = cursoRepository;
    }

    @Override
    public Prestamo guardarDesdeDTO(PrestamoDTO dto) {

        Prestamo prestamo = new Prestamo();
        prestamo.setFechaPrestamo(dto.getFechaPrestamo());

        prestamo.setCodProfesor(
                profesorRepository.findById(
                        dto.getProfesorId())
                .orElseThrow(() -> new RuntimeException("Profesor no existe "))
        );
    

        prestamo.setCodLibro(
                libroRepository.findById(dto.getLibroId())
                .orElseThrow(() -> new RuntimeException("Libro no existe "))
        );
        prestamo.setCodAsignatura(
                asignaturaRepository.findById(dto.getAsignaturaId())
                .orElseThrow(() -> new RuntimeException("Libro no existe "))

        );
        prestamo.setCodCurso(
                cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new RuntimeException("Libro no existe "))

        );
        return prestamoRepository.save(prestamo);
    }

    @Override
    public Prestamo actualizar(Long id, Prestamo prestamo) {
        Prestamo existente = buscarPorId(id);
        if (existente == null) return null;
        existente.setFechaPrestamo(prestamo.getFechaPrestamo());

        // Actualizar profesor , libro , asignatura, curso
        existente.setCodProfesor(
                profesorRepository.findById(
                        prestamo.getCodProfesor().getId()
                ).orElseThrow(() -> new RuntimeException("Profesor no existe "))
        );

        existente.setCodLibro(
                libroRepository.findById(
                        prestamo.getCodLibro().getId()
                ).orElseThrow(() -> new RuntimeException("Libro no existe "))
        );

        existente.setCodAsignatura(
                asignaturaRepository.findById(
                        prestamo.getCodProfesor().getId()
                ).orElseThrow(() -> new RuntimeException("Asignatura no existe "))
        );

        existente.setCodCurso(
                cursoRepository.findById(
                        prestamo.getCodProfesor().getId()
                ).orElseThrow(() -> new RuntimeException("Curso no existe "))
        );

        return prestamoRepository.save(existente);
    }

    @Override
    public Prestamo buscarPorId(Long id) {
        return prestamoRepository.findById(id).orElse(null);
    }

    public void eliminar(Long Id) {
        prestamoRepository.deleteById(Id);
    }


    public List<Prestamo> listarTodos() {
        return prestamoRepository.findAll();
    }
}