package com.elias.editorial.service;

import com.elias.editorial.model.Profesor;
import com.elias.editorial.repository.ProfesorRepository;
import com.elias.editorial.repository.ColegioRepository;
import com.elias.editorial.dto.ProfesorDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServiceImpl  implements ProfesorService {

    private final ProfesorRepository profesorRepository;
    private final ColegioRepository colegioRepository;

    // Inyeccion por constrctor
    public ProfesorServiceImpl(ProfesorRepository profesorRepository, ColegioRepository colegioRepository ){
        this.profesorRepository = profesorRepository;
        this.colegioRepository = colegioRepository;
    }

    @Override
    public Profesor guardarDesdeDTO (ProfesorDTO  dto){
        Profesor profesor = new Profesor();
        profesor.setNomProfesor (dto.getNombre());

        profesor.setCodColegio(
                colegioRepository.findById(dto.getCodColegio())
                        .orElseThrow(() -> new RuntimeException("Colegio no existe "))
                // si opcional esta vacio lanza una excepcion
        );
        return profesorRepository.save(profesor);
    }

    @Override
    public List<Profesor> listarTodos () {
        return profesorRepository.findAll();
    }
    @Override
    public Profesor buscarPorId (Long id ){
        return profesorRepository.findById(id).orElse(null);
    }
    @Override
    public Profesor actualizar (Long id , Profesor profesor ){
        Profesor existente = buscarPorId(id);
        if (existente == null ) return null;

        existente.setNomProfesor (profesor.getNomProfesor());

        existente.setCodColegio(
                colegioRepository.findById(profesor.getCodColegio().getId())
                        .orElseThrow(() -> new RuntimeException("Colegio no existe "))
        );
        return profesorRepository.save(existente);
    }

    @Override
    public void eliminar (Long id ){
        profesorRepository.deleteById(id);
    }
}
