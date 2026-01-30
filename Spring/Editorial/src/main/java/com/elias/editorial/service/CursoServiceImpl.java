package com.elias.editorial.service;

import com.elias.editorial.model.Curso;
import com.elias.editorial.dto.CursoDTO;
import com.elias.editorial.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl  implements CursoService {

    private final CursoRepository cursoRepository;

    public CursoServiceImpl (CursoRepository cursoRepository ){
        this.cursoRepository= cursoRepository;

    }


}
