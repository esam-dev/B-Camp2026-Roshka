package com.elias.editorial.service;

import com.elias.editorial.dto.LibroDTO;
import com.elias.editorial.model.Libro;
import com.elias.editorial.repository.LibroRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class LibroServiceImpl implements  LibroService {

    private final LibroRepository libroRepository ;

    public LibroServiceImpl (LibroRepository libroRepository){
        this.libroRepository = libroRepository;
    }

    @Override
    public Libro guardarDesdeDTO (LibroDTO dto ){
        Libro l = new Libro();
        l.setId(dto.getCodLibro());
        l.setEditorial(dto.getEditorial());
        l.setTitulo(dto.getTitulo());
        return libroRepository.save(l);
    }

    @Override
    public List<Libro> listarTodos (){
        return libroRepository.findAll();
    }

    @Override
    public Libro buscarPorId (Long id ){
        return libroRepository.findById(id).orElse(null);
    }

    @Override
    public Libro actualizar (Long id , Libro libro){
        Libro existente = buscarPorId(id);
        if( existente == null) return null;
        existente.setTitulo(libro.getTitulo());
        existente.setEditorial(libro.getTitulo());
        return libroRepository.save(existente);
    }

    @Override
    public void eliminar (Long id ){
        libroRepository.deleteById(id);
    }
}
