package com.weg.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

import com.weg.crud.dto.DepartamentoDTO;
import com.weg.crud.model.Departamento;
import com.weg.crud.service.DepartamentoService;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepartamentoDTO cadastrar(@RequestBody Departamento departamento){
        Departamento salvo = service.cadastrar(departamento);
        return new DepartamentoDTO(salvo.getId(), salvo.getNome());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DepartamentoDTO>listar(){
        return service.listarTodos().stream()
        .map(d -> new DepartamentoDTO(d.getId(),d.getNome()))
        .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DepartamentoDTO buscarPorId(@PathVariable Long id){
        Departamento dp = service.buscarPorId(id);
        return new DepartamentoDTO(dp.getId(), dp.getNome());
    }
    
}
