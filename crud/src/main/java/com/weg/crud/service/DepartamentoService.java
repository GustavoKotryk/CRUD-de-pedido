package com.weg.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weg.crud.model.Departamento;
import com.weg.crud.repository.DepartamentoRepository;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository repository;

    public Departamento cadastrar(Departamento departamento){
        return repository.save(departamento);
    }

    public List<Departamento> listarTodos(){
        return repository.findAll();
    }

    public Departamento buscarPorId(Long id){
        return repository.findById(id)
        .orElseThrow(() -> new RuntimeException ("Departamento não encontrado"));
    }


    
}
