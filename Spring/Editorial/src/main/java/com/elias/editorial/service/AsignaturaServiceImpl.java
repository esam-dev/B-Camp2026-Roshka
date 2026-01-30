package com.elias.editorial.service;

import com.elias.editorial.model.Asignatura;
import com.elias.editorial.dto.AsignaturaDTO;
import com.elias.editorial.repository.AsignaturaRepository;
import com.elias.editorial.service.AsignaturaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    private final AsignaturaRepository asignaturaRepository;

    public AsignaturaServiceImpl ( AsignaturaRepository asignaturaRepository){
        this.asignaturaRepository = asignaturaRepository;
    }

    @Override
    public Asignatura guardarDesdeDTO (AsignaturaDTO dto){
        Asignatura a = new Asignatura();
        a.setNomAsignatura(dto.getNomAsignatura());
        a.setCodAsignatura(dto.getCodAsignatura());
        return asignaturaRepository.save(a);
    }

    @Override
    public List<Asignatura> listarTodos (){

        return asignaturaRepository.findAll();
    }

    @Override
    public Asignatura buscarPorId (Long id ){   
        return asignaturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));
    }

    @Override
    public Asignatura actualizar (Long id , Asignatura asignatura ) {
        Asignatura existente = buscarPorId(id);
        existente.setNomAsignatura(asignatura.getNomAsignatura());
        existente.setCodAsignatura(asignatura.getCodAsignatura());
        return asignaturaRepository.save(existente);
    }

    @Override
    public void eliminar (Long id ){
        if (!asignaturaRepository.existsById(id)) {
            throw new RuntimeException("Asignatura no encontrada");
        }
        asignaturaRepository.deleteById(id);
    }
}
