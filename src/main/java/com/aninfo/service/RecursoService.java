package com.aninfo.service;

import com.aninfo.model.Recurso;
import com.aninfo.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository recursoRepository;

    public Collection<Recurso> getRecurso() {
        Collection<Recurso> recursos = new ArrayList<>();
        recursoRepository.findAll().forEach(recursos::add);
        return recursos;
    }

    public Optional<Recurso> getRecursoByLegajo(long legajo) {
        return recursoRepository.findById(legajo);
    }

}
