package com.weg.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weg.crud.dto.FuncionarioRequestDTO;
import com.weg.crud.model.Departamento;
import com.weg.crud.model.Funcionario;
import com.weg.crud.repository.DepartamentoRepository;
import com.weg.crud.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;
    
    public Funcionario cadastar(FuncionarioRequestDTO dto){
        Departamento departamento = departamentoRepository.findById(dto.departamentoId())
    .orElseThrow(() -> new RuntimeException("Departamento não encontrado"));

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.nome());
        funcionario.setDepartamento(departamento);

        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> listarTodos(){
        return funcionarioRepository.findAll();
    }

    public Funcionario buscarPorId(Long id){
        return funcionarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    public List<Funcionario> buscarPorDepartamentoId(Long dpId){
        return funcionarioRepository.findByDepartamentoId(dpId);
    }

    public List<Funcionario> buscarPorNome(String nome){
        return funcionarioRepository.findByNomeContainingIgnoreCase(nome);
    }
}
