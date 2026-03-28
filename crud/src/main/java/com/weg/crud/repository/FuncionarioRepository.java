package com.weg.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weg.crud.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
    List<Funcionario> findByDepartamentoId(Long departamentoId);
    List<Funcionario> findByNomeContainingIgnoreCase(String nome);
    
}
