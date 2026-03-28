package com.weg.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weg.crud.model.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    
}
