package com.weg.crud.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.weg.crud.dto.FuncionarioDTO;
import com.weg.crud.dto.FuncionarioRequestDTO;
import com.weg.crud.model.Funcionario;
import com.weg.crud.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    
    @Autowired
    private FuncionarioService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FuncionarioDTO cadastrar(@RequestBody FuncionarioRequestDTO dto){
        Funcionario salvo = service.cadastar(dto);
        return new FuncionarioDTO(salvo.getId(), salvo.getNome(), salvo.getDepartamento().getId());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FuncionarioDTO> listar(){
        return service.listarTodos().stream()
        .map(f -> new FuncionarioDTO(f.getId(), f.getNome(), f.getDepartamento().getId()))
        .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FuncionarioDTO buscarPorId(@PathVariable Long id){
        Funcionario func = service.buscarPorId(id);

        return new FuncionarioDTO(func.getId(), func.getNome(), func.getDepartamento().getId());
    }

    @GetMapping("/departamento/{dpId}")
    @ResponseStatus(HttpStatus.OK)
    public List<FuncionarioDTO> buscarPorDepartamento(@PathVariable Long dpId){
        return service.buscarPorDepartamentoId(dpId).stream()
        .map(f -> new FuncionarioDTO(f.getId(), f.getNome(), f.getDepartamento().getId()))
        .collect(Collectors.toList());
    }

    @GetMapping("/buscar")
    @ResponseStatus(HttpStatus.OK)
    public List<FuncionarioDTO> buscarPorNome(@RequestParam String nome){
        return service.buscarPorNome(nome).stream()
        .map(f -> new FuncionarioDTO(f.getId(), f.getNome(), f.getDepartamento().getId()))
        .collect(Collectors.toList());
    }
}
