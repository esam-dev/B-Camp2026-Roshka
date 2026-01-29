package com.elias.editorial.service;

import com.elias.editorial.dto.ColegioDTO;
import com.elias.editorial.model.Colegio;
import com.elias.editorial.repository.ColegioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColegioServiceImpl {

    private final ColegioRepository colegioRepository;

    public ColegioServiceImpl(ColegioRepository colegioRepository){
        this.colegioRepository = colegioRepository;
    }

    @Override
    public Colegio guardarDesdeDTO (ColegioDTO dto){
        Colegio c = new Colegio();
        c.setNomColegio(dto.getNomColegio());
        c.setId(dto.getCodColegio());
        return colegioRepository.save(c);
    }

    @Override
    public List<Colegio> listarTodos (){
        return colegioRepository.findAll();
    }

    @Override
    public Colegio buscarPorId (Long id ){
        colegioRepository.findById(id).orElse(null);
    }

    @Override
    public Colegio actualizar (Long id , Colegio colegio ) {
        Colegio existente = buscarPorId(id);
        if (existente == null) return null;
        existente.setNomColegio(colegio.getNomColegio());
        return colegioRepository.save(existente);
    }

    @Override
    public void eliminar (Long id ){
        colegioRepository.deleteById(id);
    }


}
